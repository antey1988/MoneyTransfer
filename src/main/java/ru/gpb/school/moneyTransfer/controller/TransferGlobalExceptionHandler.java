package ru.gpb.school.moneyTransfer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gpb.school.moneyTransfer.exeption_handling.NoSuchTransferException;
import ru.gpb.school.moneyTransfer.exeption_handling.TransferIncorrectData;

@ControllerAdvice
public class TransferGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<TransferIncorrectData> handleException (NoSuchTransferException noSuchTransferException){
        TransferIncorrectData transferIncorrectData = new TransferIncorrectData(noSuchTransferException.getMessage());
        return new ResponseEntity<>(transferIncorrectData, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<TransferIncorrectData> handleException(Exception exception){
        TransferIncorrectData transferIncorrectData = new TransferIncorrectData(exception.getMessage());
        return new ResponseEntity<>(transferIncorrectData, HttpStatus.BAD_REQUEST);
    }
}
