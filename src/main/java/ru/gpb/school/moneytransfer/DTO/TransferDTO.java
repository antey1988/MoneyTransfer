package ru.gpb.school.moneytransfer.dto;

import lombok.Data;

@Data
public class TransferDto {
    private String from;
    private String to;
    private Float amount;
}



