package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class DuplicateRatingException extends RuntimeException {
    public DuplicateRatingException(Long applicationId) {
        super("A rating has already been submitted for application " + applicationId + " from this side.");
    }
}