package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class DuplicateApplicationException extends RuntimeException {
    public DuplicateApplicationException(Long jobId, Long workerId) {
        super("Worker " + workerId + " has already applied to job " + jobId);
    }
}