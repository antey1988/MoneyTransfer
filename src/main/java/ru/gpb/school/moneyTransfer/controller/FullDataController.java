package ru.gpb.school.moneyTransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.moneyTransfer.exeption_handling.NoSuchTransferException;
import ru.gpb.school.moneyTransfer.exeption_handling.TransferIncorrectData;
import ru.gpb.school.moneyTransfer.model.Transfer;
import ru.gpb.school.moneyTransfer.repositories.TransferRepo;
import ru.gpb.school.moneyTransfer.repositories.TransferUserRepo;
import ru.gpb.school.moneyTransfer.service.TransferService;

import java.util.*;
@PreAuthorize("hasAuthority('ACCOUNT_SERVICE')||hasAuthority('CREDIT_RETAIL')||hasAuthority('CREDIT_CORPORATE')" +
        "||hasAuthority('INVESTING_SHARES')||hasAuthority('INVESTING_OBLIGATION')||hasAuthority('Deposit')")
@RestController
@RequestMapping("/api")
public class FullDataController {
    @Autowired
    TransferRepo transferRepo;
    @Autowired
    TransferUserRepo transferUserRepo;
    @Autowired
    TransferService transferService;
    @GetMapping("/transfers")
    public List<Transfer> findAl(){
        return transferRepo.findAll();
    }

    @GetMapping("/transfer/{Id}")
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
    @ExceptionHandler
    public ResponseEntity<TransferIncorrectData> handleException (NoSuchTransferException noSuchTransferException){
        TransferIncorrectData transferIncorrectData = new TransferIncorrectData(noSuchTransferException.getMessage());
        return new ResponseEntity<>(transferIncorrectData, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<TransferIncorrectData> handleException(Exception exception){
        TransferIncorrectData transferIncorrectData = new TransferIncorrectData(exception.getMessage());
        return new ResponseEntity<>(transferIncorrectData, HttpStatus.BAD_REQUEST);
    }
    @GetMapping
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
        return transferList;
    }

}
