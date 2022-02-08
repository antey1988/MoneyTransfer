package ru.gpb.school.moneytransfer.DTO;

import lombok.Data;

@Data
public class RecipientDTO {
    public int transferId;
    public Long recipientAccount;
    private Float amountOfMoney;
}
