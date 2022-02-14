package ru.gpb.school.moneytransfer.controller;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;
import ru.gpb.school.moneytransfer.service.TransferService;

import java.util.List;

@RestController
public class TransferController {

    @Autowired
    TransferService transferService;

    @GetMapping("/getAll")
    public List<Transfer> getAll(){
        return transferService.findAll();
    }

    @GetMapping("/history/{id}")
    public List<Transfer> getTransferById(@PathVariable String id){
        return transferService.findTransfersBySenderId(id);
    }

    @PostMapping("/save")
    public int saving(@RequestBody TransferDto transferDto){
        Transfer transfer = transferService.makeTransfer(transferDto);
        System.out.println(transfer);
        transferService.saveTransfer(transfer);

        return ExpiresFilter.XHttpServletResponse.SC_OK;
    }

}
