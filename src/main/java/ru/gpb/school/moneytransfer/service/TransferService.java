package ru.gpb.school.moneytransfer.service;

import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.model.Transfer;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferService {
    public void saveTransfer(Transfer transfer);
    public List<Transfer> findAll();
    public List<Transfer> findTransfersByRecipientAccount(String account);
    public List<Transfer> findTransfersBetween(LocalDateTime start, LocalDateTime end);
    public List<Transfer> findTransfersBySenderAccount(String account);
    public Transfer dtoToTransferEntity(TransferDto transferDto);
}
