package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class NoActiveCheckInException extends RuntimeException {
    public NoActiveCheckInException(Long applicationId) {
        super("No active check-in found for application " + applicationId + ".");
    }
}