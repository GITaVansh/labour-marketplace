package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class AlreadyCheckedInException extends RuntimeException {
    public AlreadyCheckedInException(Long applicationId) {
        super("Worker is already checked in for application " + applicationId + ". Check out first.");
    }
}