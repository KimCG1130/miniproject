package com.sparta.miniproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { IllegalArgumentException.class })
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        Exception exception = new Exception();
        exception.setHttpStatus(HttpStatus.BAD_REQUEST);
        exception.setErrorMessage(ex.getMessage());

        return new ResponseEntity(
                exception,
                HttpStatus.BAD_REQUEST
        );
    }
}
