package ru.gpb.school.moneytransfer.controller;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.gpb.school.moneytransfer.dto.ReplenishmentDto;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.dto.WithdrawalDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.service.ReplenishmentService;
import ru.gpb.school.moneytransfer.service.TransferService;
import ru.gpb.school.moneytransfer.service.TransferServiceImp;
import ru.gpb.school.moneytransfer.service.WithdrawalService;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TransferController {

    private final TransferService transferService;
    private final WithdrawalService withdrawalService;
    private final ReplenishmentService replenishmentService;

    public TransferController(TransferServiceImp transferService, WithdrawalService withdrawalService, ReplenishmentService replenishmentService){
        this.transferService = transferService;
        this.withdrawalService = withdrawalService;
        this.replenishmentService = replenishmentService;
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
    public void saving(@RequestBody TransferDto transferDto){
        Transfer transfer = transferService.dtoToTransferEntity(transferDto);
        transferService.saveTransfer(transfer);
    }

    @Transactional(rollbackOn = {SQLException.class})
    @PostMapping("/make-withdrawal")
    public void saving(@RequestBody WithdrawalDto withdrawalDto){
        Transfer transfer = withdrawalService.dtoToTransferEntity(withdrawalDto);
        transferService.saveTransfer(transfer);
    }

    @Transactional(rollbackOn = {SQLException.class})
    @PostMapping("/make-replenishment")
    public void saving(@RequestBody ReplenishmentDto replenishmentDto){
        Transfer transfer = replenishmentService.dtoToTransferEntity(replenishmentDto);
        transferService.saveTransfer(transfer);
    }
}
