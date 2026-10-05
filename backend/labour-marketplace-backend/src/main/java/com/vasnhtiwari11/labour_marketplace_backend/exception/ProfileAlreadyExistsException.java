package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class ProfileAlreadyExistsException extends RuntimeException {
    public ProfileAlreadyExistsException(Long userId) {
        super("A profile already exists for user " + userId);
    }
}