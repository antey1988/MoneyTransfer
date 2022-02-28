package ru.gpb.school.moneyTransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gpb.school.moneyTransfer.Dto.RecipientDto;
import ru.gpb.school.moneyTransfer.Dto.SenderDto;
import ru.gpb.school.moneyTransfer.Dto.TransferDto;
import ru.gpb.school.moneyTransfer.model.Transfer;
import ru.gpb.school.moneyTransfer.repositories.TransferRepo;

import java.time.LocalDateTime;

@Service
public class TransferService {
    @Autowired
    TransferRepo transferRepo;
    public TransferDto createTransferDTO(Transfer transfer){
        TransferDto transferDTO = new TransferDto();
        transferDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        transferDTO.setRecipientAccount(transfer.getRecipientAccount());
        transferDTO.setSenderAccount(transfer.getSenderAccount());
        return transferDTO;
    }
    public RecipientDto createRecipientDTO(Transfer transfer){
        RecipientDto recipientDTO = new RecipientDto();
        recipientDTO.setTransferId(transfer.getId());
        recipientDTO.setRecipientAccount(transfer.getRecipientAccount());
        recipientDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        return recipientDTO;
    }
    public SenderDto createSenderDTO(Transfer transfer){
        SenderDto senderDTO = new SenderDto();
        senderDTO.setTransferId(transfer.getId());
        senderDTO.setSenderAccount(transfer.getSenderAccount());
        senderDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        return senderDTO;
    }
    public Transfer makeATransfer(TransferDto transferDto){
        return Transfer.builder().typeOfTransfer(transferDto.getTypeOfTransfer()).amountOfMoney(transferDto.getAmountOfMoney())
                .recipientAccount(transferDto.getRecipientAccount()).senderAccount(transferDto.getSenderAccount())
                .dateOfTransfer(LocalDateTime.now()).build();
    }


}
