package ru.gpb.school.moneytransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gpb.school.moneytransfer.dto.RecipientDTO;
import ru.gpb.school.moneytransfer.dto.RequestTransfer;
import ru.gpb.school.moneytransfer.dto.SenderDTO;
import ru.gpb.school.moneytransfer.dto.TransferDTO;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;

@Service
public class TransferService {

    private final TransferRepo transferRepo;

    @Autowired
    public TransferService(TransferRepo transferRepo){
        this.transferRepo = transferRepo;
    }

    public Transfer makeTransfer(RequestTransfer requestTransfer){
        Transfer transfer = Transfer.builder()
                .amountOfMoney(requestTransfer.getAmount())
                .senderAccount(requestTransfer.getFrom())
                .recipientAccount(requestTransfer.getTo())
                .build();

        return transfer;
    }

    public Transfer DtoToEntity(TransferDTO transferDTO){
        Transfer transfer = Transfer.builder()
                .amountOfMoney(transferDTO.amountOfMoney)
                .recipientAccount(transferDTO.getRecipientAccount())
                .senderAccount(transferDTO.getSenderAccount())
                .build();

        return transfer;
    }

    public TransferDTO createTransferDTO(Transfer transfer){
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        transferDTO.setRecipientAccount(transfer.getRecipientAccount());
        transferDTO.setSenderAccount(transfer.getSenderAccount());
        return transferDTO;
    }
    public RecipientDTO createRecipientDTO(Transfer transfer){
        RecipientDTO recipientDTO = new RecipientDTO();
        recipientDTO.setTransferId(transfer.getId());
        recipientDTO.setRecipientAccount(transfer.getRecipientAccount());
        recipientDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        return recipientDTO;
    }
    public SenderDTO createSenderDTO(Transfer transfer){
        SenderDTO senderDTO = new SenderDTO();
        senderDTO.setTransferId(transfer.getId());
        senderDTO.setSenderAccount(transfer.getSenderAccount());
        senderDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        return senderDTO;
    }


}
