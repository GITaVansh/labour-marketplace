package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class EmployerProfileNotFoundException extends RuntimeException {
    public EmployerProfileNotFoundException(Long employerProfileId) {
        super("No employer profile found with id " + employerProfileId);
    }
}