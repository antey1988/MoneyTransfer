package ru.gpb.school.moneytransfer.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class WithdrawalDto {
    @NotNull
    private String withdrawalAccount;
    @NotNull
    private Float amount;
}
