package ru.gpb.school.moneytransfer.DTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class SenderDTO {
    public String senderScore;
    public BigDecimal amountOfMoney;
}
