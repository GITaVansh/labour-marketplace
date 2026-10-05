package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class DuplicatePhoneNumberException extends RuntimeException{
    public DuplicatePhoneNumberException(String phoneNumber) {
        super("A user with phone number " + phoneNumber + " already exists.");
    }
}