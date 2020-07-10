package com.sakinr.airportreservationsystem.handler;

import com.sakinr.airportreservationsystem.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> exception(NotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    // If not found specific exception, use this
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
