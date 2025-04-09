package com.nhnacademy.subjectweek02.exception;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super("Resource not found: " + message + ", " + HttpStatus.NOT_FOUND);
    }
}
