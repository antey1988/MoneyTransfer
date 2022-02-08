package ru.gpb.school.moneytransfer.DTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransferDTO {
    public String senderScore;
    public String recipientScore;
    public BigDecimal amountOfMoney;
}
