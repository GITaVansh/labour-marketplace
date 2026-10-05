package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String identifier) {
        super("No payment found for " + identifier);
    }
}