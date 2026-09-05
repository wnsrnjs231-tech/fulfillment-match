package com.fulfillment.match.exception;

public class QuoteNotFoundException extends RuntimeException {

    public QuoteNotFoundException(Long id) {
        super("견적을 찾을 수 없습니다. id=" + id);
    }
}