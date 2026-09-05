package com.fulfillment.match.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShippingRequestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleShippingRequestNotFound(
            ShippingRequestNotFoundException e
    ) {
        return Map.of(
                "status", 404,
                "message", e.getMessage()
        );
    }

    @ExceptionHandler(FulfillmentCompanyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleFulfillmentCompanyNotFound(
            FulfillmentCompanyNotFoundException e
    ) {
        return Map.of(
                "status", 404,
                "message", e.getMessage()
        );
    }

    @ExceptionHandler(QuoteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleQuoteNotFound(QuoteNotFoundException e) {
        return Map.of(
                "status", 404,
                "message", e.getMessage()
        );
    }
}