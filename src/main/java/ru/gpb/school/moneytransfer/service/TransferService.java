package ru.gpb.school.moneytransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService {

    private final TransferRepo transferRepo;

    @Autowired
    public TransferService(TransferRepo transferRepo){
        this.transferRepo = transferRepo;
    }

    public void saveTransfer(Transfer transfer){
        transferRepo.save(transfer);
    }

    public List<Transfer> findAll(){
        return transferRepo.findAll();
    }


    public List<Transfer> findTransfersBySenderId(String id){
        return transferRepo.findAllByRecipientAccount(id);
    }

    public Transfer makeTransfer(TransferDto transferDto){
        return Transfer.builder()
                .amountOfMoney(transferDto.getAmount())
                .senderAccount(transferDto.getFrom())
                .recipientAccount(transferDto.getTo())
                .dateTime(LocalDateTime.now())
                .build();
    }

}
