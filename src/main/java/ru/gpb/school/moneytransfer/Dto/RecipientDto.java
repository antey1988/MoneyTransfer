package ru.gpb.school.moneytransfer.Dto;

import lombok.Data;

@Data
public class RecipientDto {
    public int transferId;
    public Long recipientAccount;
    private Float amountOfMoney;
}
