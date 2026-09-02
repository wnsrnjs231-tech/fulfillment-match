package com.fulfillment.match.exception;

public class ShippingRequestNotFoundException extends RuntimeException {

    public ShippingRequestNotFoundException(Long id) {
        super("요청을 찾을 수 없습니다. id=" + id);
    }
}