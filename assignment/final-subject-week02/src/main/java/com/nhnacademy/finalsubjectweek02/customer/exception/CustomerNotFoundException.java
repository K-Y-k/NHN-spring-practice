package com.nhnacademy.finalsubjectweek02.customer.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super("Resource not found: " + message + ", " + HttpStatus.NOT_FOUND);
    }
}
