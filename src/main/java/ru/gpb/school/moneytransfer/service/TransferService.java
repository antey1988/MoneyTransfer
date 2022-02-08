package ru.gpb.school.moneytransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gpb.school.moneytransfer.DTO.RecipientDTO;
import ru.gpb.school.moneytransfer.DTO.SenderDTO;
import ru.gpb.school.moneytransfer.DTO.TransferDTO;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;

@Service
public class TransferService {
    @Autowired
    TransferRepo transferRepo;
    public TransferDTO createTransferDTO(Transfer transfer){
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        transferDTO.setRecipientScore(transfer.getRecipientScore());
        transferDTO.setSenderScore(transfer.getSenderScore());
        return transferDTO;
    }
    public RecipientDTO createRecipientDTO(Transfer transfer){
        RecipientDTO recipientDTO = new RecipientDTO();
        recipientDTO.setRecipientScore(transfer.getRecipientScore());
        recipientDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        return recipientDTO;
    }
    public SenderDTO createSenderDTO(Transfer transfer){
        SenderDTO senderDTO = new SenderDTO();
        senderDTO.setSenderScore(transfer.getSenderScore());
        senderDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        return senderDTO;
    }


}
