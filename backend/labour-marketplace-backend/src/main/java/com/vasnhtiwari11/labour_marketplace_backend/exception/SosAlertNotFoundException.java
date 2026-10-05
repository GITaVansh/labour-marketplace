package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class SosAlertNotFoundException extends RuntimeException {
    public SosAlertNotFoundException(Long alertId) {
        super("No SOS alert found with id " + alertId);
    }
}