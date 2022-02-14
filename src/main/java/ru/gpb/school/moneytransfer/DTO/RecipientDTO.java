package ru.gpb.school.moneytransfer.dto;

import lombok.Data;

@Data
public class RecipientDTO {
    public Long transferId;
    public String recipientAccount;
    private Float amountOfMoney;
}
