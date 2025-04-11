package com.nhnacademy.finalsubjectweek02.admin.exception;

import org.springframework.http.HttpStatus;

public class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(String message) {
        super("Resource not found: " + message + ", " + HttpStatus.NOT_FOUND);
    }
}
