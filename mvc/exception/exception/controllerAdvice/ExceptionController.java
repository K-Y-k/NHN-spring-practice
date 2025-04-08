package com.nhnacademy.springbootmvc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ UserNotFoundException.class, PostNotFoundException.class })
    public String notFoundHandleException(Exception ex, Model model) {
        log.error("Not found ", ex);
        model.addAttribute("exception", ex);
        return "error";
    }

    @ExceptionHandler({ UserAlreadyExistsException.class })
    public String existHandleException(Exception ex, Model model) {
        log.error("Already exists ", ex);
        model.addAttribute("exception", ex);
        return "error";
    }
}
