package ru.gpb.school.moneytransfer.controller;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.service.TransferService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    @GetMapping("/")
    public List<Transfer> getAll(){
        return transferService.findAll();
    }

    @GetMapping("/transfers-to-client")
    public List<Transfer> getTransferByRecipient(String account){
        return transferService.findTransfersByRecipientAccount(account);
    }


    @GetMapping("/transfers-from-client")
    public List<Transfer> getTransfersBySender(String account){
        return transferService.findTransfersBySenderAccount(account);
    }

    @GetMapping("/transfers-between")
    public List<Transfer> getTransfersByDate(
            @DateTimeFormat(pattern = "dd-MM-yyyy:HH:mm:ss") LocalDateTime start,
            @DateTimeFormat(pattern = "dd-MM-yyyy:HH:mm:ss") LocalDateTime end
            ){
        return transferService.findTransfersBetween(start, end);
    }

    @Transactional(rollbackOn = {SQLException.class})
    @PostMapping("/make-transfer")
    public int saving(@RequestBody TransferDto transferDto) throws SQLException {
        Transfer transfer = transferService.dtoToTransferEntity(transferDto);
        transferService.saveTransfer(transfer);

        if(transfer.getAmountOfMoney() == 0){
            throw new SQLException();
        }

        /*
        *   Requests to another services
        *   happen here
        * */

        return ExpiresFilter.XHttpServletResponse.SC_OK;
    }

}
