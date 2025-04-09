package com.nhnacademy.subjectweek02.exception.advice;

import com.nhnacademy.subjectweek02.exception.StudentAlreadyExistsException;
import com.nhnacademy.subjectweek02.exception.StudentNotFoundException;
import com.nhnacademy.subjectweek02.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public String alreadyExistsHandler(Exception ex, Model model) {
        log.error("Already Exists : ", ex);
        model.addAttribute("exception", ex);
        model.addAttribute("errorCode", HttpStatus.BAD_REQUEST);
        return "error/error";
    }

    /**
     * 없는 리소스에 대한 접근일 경우 Controller 기반 예외 처리 방법을 이용해서 Http Status Code 404로 응답한다.
     */
    @ExceptionHandler(StudentNotFoundException.class)
    public String notFoundHandler(Exception ex, Model model) {
        log.error("Not Exists : ", ex);
        model.addAttribute("exception", ex);
        model.addAttribute("errorCode", HttpStatus.NOT_FOUND);
        return "error/error";
    }

    @ExceptionHandler(ValidationFailedException.class)
    public String validationFailedHandler(ValidationFailedException ex, Model model) {
        log.error("validationFailed : ", ex.getMessage());
        model.addAttribute("exception", ex.getMessage());
        model.addAttribute("errorCode", HttpStatus.BAD_REQUEST);
        return "error/error";
    }
}
