package ru.gpb.school.moneytransfer.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class TransferDto {
    private String from;
    private String to;
    private Float amount;
}



