package ru.gpb.school.moneyTransfer.Dto;

import lombok.Data;

@Data
public class RecipientDto {
    public int transferId;
    public Long recipientAccount;
    private Float amountOfMoney;
}
