package com.nhnacademy.subjectweek02.exception;

public class StudentNotExistsException extends RuntimeException {
    public StudentNotExistsException(String message) {
        super(message);
    }
}
