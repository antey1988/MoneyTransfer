package ru.gpb.school.moneytransfer.dto;

import lombok.Data;

@Data
public class RequestTransfer {
    private String from;
    private String to;
    private Float amount;
}
