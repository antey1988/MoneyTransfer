package ru.gpb.school.moneytransfer.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gpb.school.moneytransfer.dto.ErrorDto;

import java.sql.SQLException;


@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<ErrorDto> handleSqlException (SQLException ex) {
        ErrorDto errorDto = new ErrorDto(-1, ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.error(errorDto.toString());
        return new ResponseEntity<>(errorDto, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({TransferException.class})
    public ResponseEntity<ErrorDto> handleTransferException (TransferException ex) {
        ErrorDto errorDto = new ErrorDto(-2, ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.error(errorDto.toString());
        return new ResponseEntity<>(errorDto, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
