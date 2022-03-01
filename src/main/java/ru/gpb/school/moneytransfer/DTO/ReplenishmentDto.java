package ru.gpb.school.moneytransfer.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ReplenishmentDto {

    @NotNull
    private String replenishmentAccount;
    @NotNull
    private Float amount;
}
