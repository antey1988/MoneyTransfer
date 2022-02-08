package ru.gpb.school.moneytransfer.DTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class RecipientDTO {
    public String recipientScore;
    private BigDecimal amountOfMoney;
}
