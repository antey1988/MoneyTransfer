package ru.gpb.school.moneytransfer.dto;

import lombok.Data;

@Data
public class WithdrawalDto {
    private String withdrawalAccount;
    private Float amount;
}
