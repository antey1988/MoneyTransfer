package ru.gpb.school.moneytransfer.Dto;

import lombok.Data;

@Data
public class SenderDto {
    public int transferId;
    public Long senderAccount;
    public Float amountOfMoney;
}
