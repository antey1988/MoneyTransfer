package ru.gpb.school.moneytransfer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "API для работы с сервисов переводов")
public class TransferController {

    private final TransferService transferService;
    private final WithdrawalService withdrawalService;
    private final ReplenishmentService replenishmentService;

    public TransferController(TransferService transferService, WithdrawalService withdrawalService, ReplenishmentService replenishmentService){
        this.transferService = transferService;
        this.withdrawalService = withdrawalService;
        this.replenishmentService = replenishmentService;
    }

    @Operation(summary = "Список всех переводов: снятие, пополнение и переводы между счетами")
    @GetMapping("/transfers")
    public List<Transfer> getAll(){
        return transferService.findAll();
    }


    @Operation(summary = "Список переводов на указанный счет клиента")
    @GetMapping("/transfers-to-client")
    public List<Transfer> getTransferByRecipient(String account){
        return transferService.findTransfersByRecipientAccount(account);
    }


    @Operation(summary = "Список переводов с указанного счета клиента")
    @GetMapping("/transfers-from-client")
    public List<Transfer> getTransfersBySender(String account){
        return transferService.findTransfersBySenderAccount(account);
    }

    @Operation(summary = "Список всех переводов в интервале дат")
    @GetMapping("/transfers-between")
    public List<Transfer> getTransfersByDate(
            @DateTimeFormat(pattern = "dd-MM-yyyy:HH:mm:ss") LocalDateTime start,
            @DateTimeFormat(pattern = "dd-MM-yyyy:HH:mm:ss") LocalDateTime end
            ){
        return transferService.findTransfersBetween(start, end);
    }

    @Operation(summary = "Перевод со счета на счет клиента")
    @Transactional(rollbackOn = {SQLException.class})
    @PostMapping("/make-transfer")
    public void saving(@RequestBody TransferDto transferDto){
        Transfer transfer = transferService.dtoToTransferEntity(transferDto);
        transferService.saveTransfer(transfer);
    }

    @Operation(summary = "Пополнение счета клиента")
    @Transactional(rollbackOn = {SQLException.class})
    @PostMapping("/make-withdrawal")
    public void saving(@RequestBody WithdrawalDto withdrawalDto){
        Transfer transfer = withdrawalService.dtoToTransferEntity(withdrawalDto);
        transferService.saveTransfer(transfer);
    }

    @Operation(summary = "Снятие со счета клиента")
    @Transactional(rollbackOn = {SQLException.class})
    @PostMapping("/make-replenishment")
    public void saving(@RequestBody ReplenishmentDto replenishmentDto){
        Transfer transfer = replenishmentService.dtoToTransferEntity(replenishmentDto);
        transferService.saveTransfer(transfer);
    }
}
