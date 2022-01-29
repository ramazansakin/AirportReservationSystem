package com.sakinr.airportreservationsystem.handler;

import com.sakinr.airportreservationsystem.exception.NotFoundException;
import com.sakinr.airportreservationsystem.exception.QuotaIsFullException;
import com.sakinr.airportreservationsystem.model.ValidationErrorResponse;
import com.sakinr.airportreservationsystem.model.Violation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map> exception(NotFoundException exception) {
        // You can define any other class for better visualization for response
        Map<String, String> response = new HashMap<>();
        response.put("cause", exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> onConstraintValidationException(
            ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(
                            violation.getPropertyPath().toString(),
                            violation.getMessage()
                    )
            );
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(
                            fieldError.getField(),
                            fieldError.getDefaultMessage()
                    )
            );
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuotaIsFullException.class)
    public ResponseEntity<Object> onQuotaIsFullException(QuotaIsFullException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> onPSQLException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // If not found specific exception, use this
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>("Generic Exception details : " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
