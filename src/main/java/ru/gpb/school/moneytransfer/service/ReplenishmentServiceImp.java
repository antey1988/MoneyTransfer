package ru.gpb.school.moneytransfer.service;

import org.springframework.stereotype.Service;
import ru.gpb.school.moneytransfer.dto.ReplenishmentDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.model.type.TransferType;

import java.time.LocalDateTime;

@Service
public class ReplenishmentServiceImp implements ReplenishmentService{
    @Override
    public Transfer dtoToTransferEntity(ReplenishmentDto replenishmentDto) {
        return Transfer.builder()
                .amountOfMoney(replenishmentDto.getAmount())
                .senderAccount("---")
                .recipientAccount(replenishmentDto.getAccount())
                .dateTime(LocalDateTime.now())
                .transferType(TransferType.REPLENISHMENT)
                .build();
    }
}
