package ru.gpb.school.moneytransfer.service;

import org.springframework.stereotype.Service;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.exceptions.TransferException;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepo transferRepo;

    public TransferServiceImpl(TransferRepo transferRepo){
        this.transferRepo = transferRepo;
    }

    public void saveTransfer(Transfer transfer){
        transferRepo.save(transfer);
    }

    public List<Transfer> findAll(){
        return transferRepo.findAll();
    }


    public List<Transfer> findTransfersByRecipientAccount(String id){
        return transferRepo.findAllByRecipientAccount(id);
    }

    public List<Transfer> findTransfersBetween(LocalDateTime start, LocalDateTime end){
        return transferRepo.findByDateTimeGreaterThanAndDateTimeLessThan(start, end);
    }

    public List<Transfer> findTransfersBySenderAccount(String id){
        return transferRepo.findAllBySenderAccount(id);
    }

    private Transfer dtoToTransferEntity(TransferDto transferDto)  {
        try {
            if (transferDto.getAmount() < 10) throw new TransferException(-20, "Amount must be greater than 10");
        } catch (TransferException e) {
            e.printStackTrace();
        }
        return Transfer.builder()
                .amountOfMoney(transferDto.getAmount())
                .senderAccount(transferDto.getFrom())
                .recipientAccount(transferDto.getTo())
                .dateTime(LocalDateTime.now())
                .build();
    }
    public int makeTransfer(TransferDto transferDto) {
        Transfer transfer = dtoToTransferEntity(transferDto);
        transferRepo.save(transfer);
        return 0;
    }



}
