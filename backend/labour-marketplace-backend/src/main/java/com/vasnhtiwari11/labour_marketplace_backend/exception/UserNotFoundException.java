package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super("No user found with id " + userId);
    }

    public UserNotFoundException(String phoneNumber) {
        super("No user found with phone number " + phoneNumber);
    }
}