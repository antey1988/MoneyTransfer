package ru.gpb.school.moneyTransfer.controller;


import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.gpb.school.moneyTransfer.Dto.TransferDto;
import ru.gpb.school.moneyTransfer.exeption_handling.NoSuchTransferException;
import ru.gpb.school.moneyTransfer.exeption_handling.TransferIncorrectData;
import ru.gpb.school.moneyTransfer.model.Transfer;
import ru.gpb.school.moneyTransfer.model.TransferUser;
import ru.gpb.school.moneyTransfer.repositories.TransferRepo;
import ru.gpb.school.moneyTransfer.repositories.TransferUserRepo;
import ru.gpb.school.moneyTransfer.service.TransferService;
import net.minidev.json.JSONObject;

import java.util.*;

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

    @PostMapping("/makeTransfer")
    public HttpStatus makeATransfer(@RequestBody TransferDto transferDto){
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

}
