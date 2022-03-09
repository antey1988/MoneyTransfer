package ru.gpb.school.moneyTransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gpb.school.moneyTransfer.Dto.FullTransferDto;
import ru.gpb.school.moneyTransfer.Dto.RecipientDto;
import ru.gpb.school.moneyTransfer.Dto.SenderDto;
import ru.gpb.school.moneyTransfer.Dto.StatisticTransferDto;
import ru.gpb.school.moneyTransfer.model.Transfer;
import ru.gpb.school.moneyTransfer.repositories.TransferRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {
    @Autowired
    TransferRepo transferRepo;
    public FullTransferDto createFullTransferDTO(Transfer transfer){
        FullTransferDto transferDTO = new FullTransferDto();
        transferDTO.setAmountOfMoney(transfer.getAmountOfMoney());
        transferDTO.setRecipientAccount(transfer.getRecipientAccount());
        transferDTO.setSenderAccount(transfer.getSenderAccount());
        return transferDTO;
    }
    public List<FullTransferDto> createFullTransfersDTO(List<Transfer> transfers){
        List<FullTransferDto> list = new ArrayList<>();
        for(int i=0;i<transfers.size();i++){
            list.add(createFullTransferDTO(transfers.get(i)));
        }
        return list;
    }
    public StatisticTransferDto createStatisticTransferDto(Transfer transfer){
        StatisticTransferDto statisticTransferDto = new StatisticTransferDto();
        statisticTransferDto.setDateOfTransfer(transfer.getDateOfTransfer());
        statisticTransferDto.setTypeOfTransfer(transfer.getTypeOfTransfer());
        statisticTransferDto.setAmountOfMoney(transfer.getAmountOfMoney());
        return statisticTransferDto;
    }
    public List<StatisticTransferDto> createListStatisticTransferDto(List<Transfer> transfers){
        List<StatisticTransferDto>list = new ArrayList<>();
        for(int i=0;i<transfers.size();i++){
            list.add(createStatisticTransferDto(transfers.get(i)));
        }
        return list;
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
    public Transfer makeATransfer(FullTransferDto transferDto){
        return Transfer.builder().typeOfTransfer(transferDto.getTypeOfTransfer()).amountOfMoney(transferDto.getAmountOfMoney())
                .recipientAccount(transferDto.getRecipientAccount()).senderAccount(transferDto.getSenderAccount())
                .dateOfTransfer(LocalDateTime.now()).build();
    }


}
