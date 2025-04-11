package com.nhnacademy.finalsubjectweek02.inquiry.exception;

import org.springframework.http.HttpStatus;

public class InquiryNotFoundException extends RuntimeException {
    public InquiryNotFoundException(String message) {
        super("Resource not found: " + message + ", " + HttpStatus.NOT_FOUND);
    }
}
