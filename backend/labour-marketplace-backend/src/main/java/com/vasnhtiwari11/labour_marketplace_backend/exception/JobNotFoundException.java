package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(Long jobId) {
        super("No job found with id " + jobId);
    }
}