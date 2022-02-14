package ru.gpb.school.moneytransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.moneytransfer.dto.RequestTransfer;
import ru.gpb.school.moneytransfer.dto.TransferDTO;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;
import ru.gpb.school.moneytransfer.service.TransferService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TransferController {

    @Autowired
    TransferRepo transferRepo;

    @Autowired
    TransferService transferService;

    @GetMapping("/getAll")
    public List<Transfer> getAll(){
        return transferRepo.findAll();
    }

    @PostMapping("/save")
    public Transfer saving(@RequestBody RequestTransfer requestTransfer){
        Transfer transfer = transferService.makeTransfer(requestTransfer);
        System.out.println(transfer);
        transferRepo.save(transfer);
        return transfer;
    }

}
