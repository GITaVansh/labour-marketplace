package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException(Long applicationId) {
        super("No application found with id " + applicationId);
    }
}