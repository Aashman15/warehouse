package com.aashman.warehouseb.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("status", false);
        return "status";
    }

}


