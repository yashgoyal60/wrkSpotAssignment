package com.wrkspotassignment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.wrkspotassignment.constants.ErrorMessages.CUSTOMER_LIST_SAVE_GENERIC_UNEXPECTED_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerCreationException.class)
    public ResponseEntity<String> handleException(CustomerCreationException e) {
        return new ResponseEntity<>(CUSTOMER_LIST_SAVE_GENERIC_UNEXPECTED_ERROR + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
