package ru.gpb.school.moneytransfer.controller;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.moneytransfer.dto.ReplenishmentDto;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.dto.WithdrawalDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.service.TransferService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

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
    public int saving(@RequestBody TransferDto transferDto){
        Transfer transfer = transferService.transferDtoToTransferEntity(transferDto);
        transferService.saveTransfer(transfer);

        return ExpiresFilter.XHttpServletResponse.SC_OK;
    }

    @Transactional(rollbackOn = {SQLException.class})
    @PostMapping("/make-withdrawal")
    public int saving(@RequestBody WithdrawalDto withdrawalDto){
        Transfer transfer = transferService.withdrawalDtoToTransferEntity(withdrawalDto);
        transferService.saveTransfer(transfer);

        return ExpiresFilter.XHttpServletResponse.SC_OK;
    }

    @Transactional(rollbackOn = {SQLException.class})
    @PostMapping("/make-replenishment")
    public int saving(@RequestBody ReplenishmentDto replenishmentDto){
        Transfer transfer = transferService.replenishmentDtoToTransferEntity(replenishmentDto);
        transferService.saveTransfer(transfer);

        return ExpiresFilter.XHttpServletResponse.SC_OK;
    }
}
