package ru.gpb.school.moneytransfer.dto;

import lombok.Data;

@Data
public class ReplenishmentDto {
    private String replenishmentAccount;
    private Float amount;
}
