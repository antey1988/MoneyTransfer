package ru.gpb.school.moneytransfer.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class TransferDTO {
    public Long transferId;
    public String senderAccount;
    public String recipientAccount;
    public Float amountOfMoney;
}



