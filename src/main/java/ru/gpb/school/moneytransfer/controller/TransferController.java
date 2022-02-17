package ru.gpb.school.moneytransfer.controller;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.exceptions.TransferException;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.service.TransferServiceImpl;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
public class TransferController {

    private final TransferServiceImpl transferServiceImpl;

    @Autowired
    public TransferController(TransferServiceImpl transferServiceImpl){
        this.transferServiceImpl = transferServiceImpl;
    }

    @GetMapping("/getAll")
    public List<Transfer> getAll(){
        return transferServiceImpl.findAll();
    }

    @GetMapping("/history/recipient/{id}")
    public List<Transfer> getTransferByRepipient(@PathVariable String id){
        return transferServiceImpl.findTransfersByRecipientAccount(id);
    }

    @GetMapping(value = "/history/sender/{id}")
    public List<Transfer> getTransfersBySender(@PathVariable String id){
        return transferServiceImpl.findTransfersBySenderAccount(id);
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
            return transferServiceImpl.findAll();
        }
        LocalDateTime start = LocalDateTime.of(localDate, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(localDate, LocalTime.MAX);
        return transferServiceImpl.findTransfersBetween(start, end);
    }

    @PostMapping("/save")
    public int saving(@RequestBody TransferDto transferDto) throws TransferException {
        transferServiceImpl.makeTransfer(transferDto);
        //transferServiceImpl.saveTransfer(transfer);

        return ExpiresFilter.XHttpServletResponse.SC_OK;
    }

}
