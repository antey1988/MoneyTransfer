package ru.gpb.school.moneytransfer.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class TransferDto {

    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    private Float amount;
}



