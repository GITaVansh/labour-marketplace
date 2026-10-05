package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class JobFullyStaffedException extends RuntimeException {
    public JobFullyStaffedException(Long jobId) {
        super("Job " + jobId + " has already reached its required number of workers.");
    }
}