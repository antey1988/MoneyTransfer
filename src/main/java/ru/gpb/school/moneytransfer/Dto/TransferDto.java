package ru.gpb.school.moneytransfer.Dto;

import lombok.Data;

@Data
public class TransferDto {
    public int transferId;
    public Long senderAccount;
    public Long recipientAccount;
    public Float amountOfMoney;
}
