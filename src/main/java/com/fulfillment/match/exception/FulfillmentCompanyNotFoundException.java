package com.fulfillment.match.exception;

public class FulfillmentCompanyNotFoundException extends RuntimeException {

    public FulfillmentCompanyNotFoundException(Long id) {
        super("업체를 찾을 수 없습니다. id=" + id);
    }
}