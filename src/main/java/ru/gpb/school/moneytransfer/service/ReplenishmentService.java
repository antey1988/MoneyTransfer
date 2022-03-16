package ru.gpb.school.moneytransfer.service;

import ru.gpb.school.moneytransfer.dto.ReplenishmentDto;
import ru.gpb.school.moneytransfer.model.Transfer;

public interface ReplenishmentService {
    public Transfer dtoToTransferEntity(ReplenishmentDto replenishmentDto);
}
