package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class InvalidRatingScoreException extends RuntimeException {
    public InvalidRatingScoreException() {
        super("Rating score must be between 1 and 5.");
    }
}