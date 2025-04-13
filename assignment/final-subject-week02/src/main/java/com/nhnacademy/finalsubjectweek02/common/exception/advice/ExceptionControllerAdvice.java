package com.nhnacademy.finalsubjectweek02.common.exception.advice;

import com.nhnacademy.finalsubjectweek02.admin.exception.AdminNotFoundException;
import com.nhnacademy.finalsubjectweek02.common.exception.ValidationFailedException;
import com.nhnacademy.finalsubjectweek02.customer.exception.CustomerNotFoundException;
import com.nhnacademy.finalsubjectweek02.inquiry.exception.InquiryAlreadyExistsException;
import com.nhnacademy.finalsubjectweek02.inquiry.exception.InquiryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({InquiryAlreadyExistsException.class})
    public String alreadyExistsHandler(Exception ex, Model model) {
        log.error("Already Exists : ", ex);
        model.addAttribute("exception", ex);
        model.addAttribute("errorCode", HttpStatus.BAD_REQUEST);
        return "error/error";
    }

    @ExceptionHandler({CustomerNotFoundException.class, AdminNotFoundException.class, InquiryNotFoundException.class})
    public String notFoundHandler(Exception ex, Model model) {
        log.error("Not Exists : ", ex);
        model.addAttribute("exception", ex);
        model.addAttribute("errorCode", HttpStatus.NOT_FOUND);
        return "error/error";
    }

    @ExceptionHandler({ValidationFailedException.class})
    public String validationFailedHandler(ValidationFailedException ex, Model model) {
        log.error("validationFailed : ", ex.getMessage());
        model.addAttribute("exception", ex.getMessage());
        model.addAttribute("errorCode", HttpStatus.BAD_REQUEST);
        return "error/error";
    }
}
