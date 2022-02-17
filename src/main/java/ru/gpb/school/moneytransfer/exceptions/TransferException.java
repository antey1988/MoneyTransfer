package ru.gpb.school.moneytransfer.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferException extends Exception {
    private int code;
    private String message;
}
