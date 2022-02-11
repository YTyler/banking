package com.ytyler.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> UserExceptionHandler(ResourceNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = e.getMessage();
        ErrorMessage errorMessage = new ErrorMessage(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidLoginException.class)
    public ResponseEntity<ErrorMessage> UserExceptionHandler(InvalidLoginException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = e.getMessage();
        ErrorMessage errorMessage = new ErrorMessage(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
