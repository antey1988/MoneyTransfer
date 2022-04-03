package ru.gpb.school.moneytransfer.service;

import org.springframework.stereotype.Service;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.model.type.TransferType;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferServiceImp implements TransferService{

    private final TransferRepo transferRepo;

    public TransferServiceImp(TransferRepo transferRepo){
        this.transferRepo = transferRepo;
    }

    public void saveTransfer(Transfer transfer){
        transferRepo.save(transfer);
    }

    public List<Transfer> findAll(){
        return transferRepo.findAll();
    }


    public List<Transfer> findTransfersByRecipientAccount(String account){
        return transferRepo.findAllByRecipientAccount(account);
    }

    public List<Transfer> findTransfersBetween(LocalDateTime start, LocalDateTime end){
        return transferRepo.findByDateTimeGreaterThanAndDateTimeLessThan(start, end);
    }

    public List<Transfer> findTransfersBySenderAccount(String account){
        return transferRepo.findAllBySenderAccount(account);
    }

    public Transfer dtoToTransferEntity(TransferDto transferDto){
        return Transfer.builder()
                .amountOfMoney(transferDto.getAmount())
                .senderAccount(transferDto.getSenderAccount())
                .recipientAccount(transferDto.getRecipientAccount())
                .dateTime(LocalDateTime.now())
                .transferType(TransferType.TRANSFER)
                .build();
    }
}
