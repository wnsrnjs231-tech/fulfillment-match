package com.fulfillment.match.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShippingRequestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleShippingRequestNotFound(
            ShippingRequestNotFoundException e,
            Model model
    ) {
        System.out.println("404 예외 처리됨");

        model.addAttribute("message", e.getMessage());
        return "error/404";
    }
}