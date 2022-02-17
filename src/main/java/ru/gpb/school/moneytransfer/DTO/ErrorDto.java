package ru.gpb.school.moneytransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private int code;
    private String message;
}
