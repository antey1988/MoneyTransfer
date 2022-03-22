package ru.gpb.school.moneyTransfer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.gpb.school.moneyTransfer.Dto.FullTransferDto;
import ru.gpb.school.moneyTransfer.Dto.TransferUserDto;
import ru.gpb.school.moneyTransfer.exeption_handling.NoSuchTransferException;
import ru.gpb.school.moneyTransfer.model.Transfer;
import ru.gpb.school.moneyTransfer.model.TransferUser;
import ru.gpb.school.moneyTransfer.repositories.TransferRepo;
import ru.gpb.school.moneyTransfer.repositories.TransferUserRepo;
import ru.gpb.school.moneyTransfer.service.TransferService;
import net.minidev.json.JSONObject;

import java.util.*;

import static ru.gpb.school.moneyTransfer.model.Role.*;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    TransferRepo transferRepo;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    TransferUserRepo transferUserRepo;
    @Autowired
    TransferService transferService;

    @PostMapping ("/addUsr")
    public TransferUser addUsr(@RequestBody TransferUserDto transferUserDto){
        TransferUser transferUser = new TransferUser();
        transferUser.setUserName(transferUserDto.getUserName());
        transferUser.setDescription(transferUserDto.getDescription());
        transferUser.setPassword(transferUserDto.getPassword());
        transferUserRepo.save(transferUser);
        return transferUser;
    }

    @PostMapping("/makeTransfer")
    public HttpStatus makeATransfer(@RequestBody FullTransferDto transferDto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username =  auth.getName();
        TransferUser user = transferUserRepo.findByUserName(username);
        Transfer transfer= transferService.makeATransfer(transferDto);
        transfer.setTypeOfTransfer(user.getRoles().toString());
        try{
            ResponseEntity<JSONObject> theAccountExist  = requestGetToUrl("AccountService/api/thisAccountExist/"+transferDto.getSenderAccount());
            if(theAccountExist.getStatusCodeValue()<200||theAccountExist.getStatusCodeValue()>300){
                return HttpStatus.resolve(504);
            }
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(transfer);
            JSONParser parser = new JSONParser();
            JSONObject transferJson = (JSONObject) parser.parse(json);
            ResponseEntity<JSONObject> createTransfer = requestPostToUrl("AccountService/api/createTransfer", transferJson);
            if(createTransfer.getStatusCodeValue()<200||createTransfer.getStatusCodeValue()>300){
                return HttpStatus.resolve(504);
            }
            this.transferRepo.save(transfer);
        }
        catch (Exception ex){
            System.out.println("Ошибка в методе Make a transfer" +  ex.toString());
        }
        return HttpStatus.resolve(200);
    }

    private ResponseEntity<JSONObject> requestGetToUrl(String url) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>( headers);
            ResponseEntity<JSONObject> res = restTemplate.exchange(url, HttpMethod.GET,entity,JSONObject.class);
            return res;
    }

    private ResponseEntity<JSONObject> requestPostToUrl(String url, JSONObject object){
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                Map<String, String> body = new ObjectMapper().readValue(object.toJSONString(), HashMap.class);
                HttpEntity<Map<String,String>> entity = new HttpEntity<>(body ,headers);
                ResponseEntity<JSONObject> res = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
                return res;
            }catch (Exception ex){
                System.out.println(ex.toString());
            }
            return new ResponseEntity<JSONObject>(null);
    }
    @GetMapping("/transfers")
    public String  getLinkForTransfers(){

       TransferUser user = getUserFromAuth();

       if(user.getRoles().contains(ACCOUNT_SERVICE)||user.getRoles().contains(CREDIT_RETAIL)||user.getRoles().contains(CREDIT_CORPORATE)
        ||user.getRoles().contains(INVESTING_SHARES)||user.getRoles().contains(INVESTING_OBLIGATION)||user.getRoles().contains(DEPOSIT)){
            return "redirect:/fullData/transfers";
        }
        if(user.getRoles().contains(STATISTICAL)){
            return "redirect:/fullData/transfers";
        }
        return "Error username data";
    }
    @GetMapping("transfers/{Id}")
    public String getLinkForTransferForId(@PathVariable String Id){
        TransferUser user = getUserFromAuth();
        if(user.getRoles().contains(ACCOUNT_SERVICE)||user.getRoles().contains(CREDIT_RETAIL)||user.getRoles().contains(CREDIT_CORPORATE)
                ||user.getRoles().contains(INVESTING_SHARES)||user.getRoles().contains(INVESTING_OBLIGATION)||user.getRoles().contains(DEPOSIT)){
            return "redirect:/fullData/transfers/"+ Id;
        }
        if(user.getRoles().contains(STATISTICAL)){
            return "redirect:/fullData/transfers/"+Id;
        }
        return "Error username data";
    }
    private TransferUser getUserFromAuth(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username =  auth.getName();
        TransferUser user = transferUserRepo.findByUserName(username);
        if(user==null){
            throw new NoSuchTransferException("this user not found");
        }
        return user;
    }
}
