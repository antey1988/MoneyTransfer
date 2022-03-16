package ru.gpb.school.moneytransfer.service;

import ru.gpb.school.moneytransfer.dto.WithdrawalDto;
import ru.gpb.school.moneytransfer.model.Transfer;

public interface WithdrawalService {
    public Transfer dtoToTransferEntity(WithdrawalDto withdrawalDto);
}
