package ru.gpb.school.moneyTransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.moneyTransfer.exeption_handling.NoSuchTransferException;
import ru.gpb.school.moneyTransfer.model.Transfer;
import ru.gpb.school.moneyTransfer.repositories.TransferRepo;
import ru.gpb.school.moneyTransfer.service.TransferService;

import java.util.*;
@PreAuthorize("hasAuthority('ACCOUNT_SERVICE')||hasAuthority('CREDIT_RETAIL')||hasAuthority('CREDIT_CORPORATE')" +
        "||hasAuthority('INVESTING_SHARES')||hasAuthority('INVESTING_OBLIGATION')||hasAuthority('DEPOSIT')")
@RestController
@RequestMapping("/api")
public class FullDataController {
    private TransferRepo transferRepo;
    private TransferService transferService;

    @GetMapping("/fullData/transfers")
    public List<Transfer> findAl(){
        return transferRepo.findAll();
    }

    @GetMapping("/fullData/transfers/{Id}")
    public Transfer getTransferById(@PathVariable String Id){
        Optional<Transfer> transferOptional;
        Transfer transfer = null;
        transferOptional = transferRepo.findById(Id);
        if(transferOptional.isPresent()){
            throw new NoSuchTransferException("there is no transfer with id" + Id + " in database");
        }
        transfer = transferOptional.get();
        return transfer;
    }

    @GetMapping("/fullData/transfers/{query}")
    public Iterable<Transfer> getTransferForQuery(@PathVariable Map<String, String> query){
        String dataCheck = query.get("dateOfTransfer");
        String amountCheck = query.get("amountOfMoney");
        String senderAccountCheck = query.get("senderAccount");
        String recipientAccountCheck = query.get("recipientAccount");
        List<Transfer> transferList = transferRepo.findAll();

        if(dataCheck!=null&&dataCheck!=""){
            for(int i=0;i<transferList.size();i++){
                if(!transferList.get(i).getDateOfTransfer().equals(dataCheck)){
                    transferList.remove(i);
                }
            }
        }

        if(amountCheck!=null&&amountCheck!=""){
            for(int i=0;i<transferList.size();i++){
                if(!transferList.get(i).getAmountOfMoney().equals(amountCheck)){
                    transferList.remove(i);
                }
            }
        }

        if(senderAccountCheck!=null&&senderAccountCheck!=""){
            for(int i=0;i<transferList.size();i++){
                if(!transferList.get(i).getSenderAccount().equals(senderAccountCheck)){
                    transferList.remove(i);
                }
            }
        }

        if(recipientAccountCheck!=null&&recipientAccountCheck!=""){
            for(int i=0;i<transferList.size();i++){
                if(!transferList.get(i).getRecipientAccount().equals(recipientAccountCheck)){
                    transferList.remove(i);
                }
            }
        }
        if(transferList==null||transferList.size()==0){
            throw new NoSuchTransferException("there is no transfers with  query =" + query);
        }
        return transferList;
    }

}
