package ru.gpb.school.moneytransfer.dto;

import lombok.Data;

@Data
public class SenderDTO {
    public Long transferId;
    public String senderAccount;
    public Float amountOfMoney;
}
