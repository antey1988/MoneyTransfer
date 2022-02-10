package ru.gpb.school.moneytransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gpb.school.moneytransfer.Dto.RecipientDto;
import ru.gpb.school.moneytransfer.Dto.SenderDto;
import ru.gpb.school.moneytransfer.Dto.TransferDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;

@Service
public class TransferService {
    @Autowired
    TransferRepo transferRepo;
    public TransferDto createTransferDTO(Transfer transfer){
        TransferDto transferDTO = new TransferDto();
        transferDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        transferDTO.setRecipientAccount(transfer.getRecipientScore());
        transferDTO.setSenderAccount(transfer.getSenderScore());
        return transferDTO;
    }
    public RecipientDto createRecipientDTO(Transfer transfer){
        RecipientDto recipientDTO = new RecipientDto();
        recipientDTO.setTransferId(transfer.getId());
        recipientDTO.setRecipientAccount(transfer.getRecipientScore());
        recipientDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        return recipientDTO;
    }
    public SenderDto createSenderDTO(Transfer transfer){
        SenderDto senderDTO = new SenderDto();
        senderDTO.setTransferId(transfer.getId());
        senderDTO.setSenderAccount(transfer.getSenderScore());
        senderDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        return senderDTO;
    }


}
