package ru.gpb.school.moneytransfer.exception;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;


@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler({SQLException.class})
    public int handleSqlException(){
        return ExpiresFilter.XHttpServletResponse.SC_NOT_IMPLEMENTED;
    }

}

