package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class WorkerProfileNotFoundException extends RuntimeException {
    public WorkerProfileNotFoundException(Long workerProfileId) {
        super("No worker profile found with id " + workerProfileId);
    }
}