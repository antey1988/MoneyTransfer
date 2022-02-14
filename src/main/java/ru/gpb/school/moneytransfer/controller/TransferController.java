package ru.gpb.school.moneytransfer.controller;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.service.TransferService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    @GetMapping("/getAll")
    public List<Transfer> getAll(){
        return transferService.findAll();
    }

    @GetMapping("/history/recipient/{id}")
    public List<Transfer> getTransferByRepipient(@PathVariable String id){
        return transferService.findTransfersByRecipientAccount(id);
    }


    @GetMapping("/history/sender/{id}")
    public List<Transfer> getTransfersBySender(@PathVariable String id){
        return transferService.findTransfersBySenderAccount(id);
    }

    @GetMapping("/getAll/{date}")
    public List<Transfer> getTransfersByDate(@PathVariable String date){
        String[] params = date.split("-");
        int day;
        int month;
        int year;
        LocalDate localDate;
        try {
             day = Integer.parseInt(params[0]);
             month = Integer.parseInt(params[1]);
             year = Integer.parseInt(params[2]);
             localDate = LocalDate.of(year, month, day);
        }catch (Exception e){
            return transferService.findAll();
        }
        LocalDateTime start = LocalDateTime.of(localDate, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(localDate, LocalTime.MAX);
        return transferService.findTransfersBetween(start, end);
    }

    @PostMapping("/save")
    public int saving(@RequestBody TransferDto transferDto){
        Transfer transfer = transferService.makeTransfer(transferDto);
        System.out.println(transfer);
        transferService.saveTransfer(transfer);

        return ExpiresFilter.XHttpServletResponse.SC_OK;
    }

}
