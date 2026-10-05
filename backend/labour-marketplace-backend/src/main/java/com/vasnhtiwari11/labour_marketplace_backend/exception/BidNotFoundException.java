package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class BidNotFoundException extends RuntimeException {
    public BidNotFoundException(Long bidId) {
        super("No bid found with id " + bidId);
    }
}