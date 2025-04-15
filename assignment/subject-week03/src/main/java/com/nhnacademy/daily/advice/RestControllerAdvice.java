package com.nhnacademy.daily.advice;


import com.nhnacademy.daily.exception.MemberAlreadyExistsException;
import com.nhnacademy.daily.exception.MemberNotFoundException;
import com.nhnacademy.daily.exception.ProjectAlreadyExistsException;
import com.nhnacademy.daily.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler({MemberNotFoundException.class, ProjectNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>("서버 오류가 발생했습니다. 해당 리소스를 발견하지 못하였습니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MemberAlreadyExistsException.class, ProjectAlreadyExistsException.class})
    public ResponseEntity<String> handleAlreadyExistsException(Exception ex) {
        return new ResponseEntity<>("서버 오류가 발생했습니다. 이미 존재하는 리소스입니다.", HttpStatus.CONFLICT);
    }
}
