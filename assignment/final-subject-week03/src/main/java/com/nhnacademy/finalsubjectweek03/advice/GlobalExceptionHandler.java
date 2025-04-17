package com.nhnacademy.finalsubjectweek03.advice;

import com.nhnacademy.finalsubjectweek03.exception.MemberAlreadyExistsException;
import com.nhnacademy.finalsubjectweek03.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MemberNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>("서버 오류가 발생했습니다. 해당 리소스를 발견하지 못하였습니다." + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MemberAlreadyExistsException.class})
    public ResponseEntity<?> handleAlreadyExistsException(Exception ex) {
        return new ResponseEntity<>("서버 오류가 발생했습니다. 이미 존재하는 리소스입니다." + ex.getMessage(), HttpStatus.CONFLICT);
    }
}
