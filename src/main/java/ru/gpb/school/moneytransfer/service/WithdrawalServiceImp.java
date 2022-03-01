package ru.gpb.school.moneytransfer.service;

import org.springframework.stereotype.Service;
import ru.gpb.school.moneytransfer.dto.WithdrawalDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.model.type.TransferType;

import java.time.LocalDateTime;

@Service
public class WithdrawalServiceImp implements WithdrawalService{
    @Override
    public Transfer dtoToTransferEntity(WithdrawalDto withdrawalDto) {
        return Transfer.builder()
                .amountOfMoney(withdrawalDto.getAmount())
                .senderAccount(withdrawalDto.getWithdrawalAccount())
                .recipientAccount("---")
                .dateTime(LocalDateTime.now())
                .transferType(TransferType.WITHDRAW)
                .build();
    }
}
