package com.vasnhtiwari11.labour_marketplace_backend.exception;

public class AttendanceNotFoundException extends RuntimeException {
    public AttendanceNotFoundException(Long attendanceId) {
        super("No attendance record found with id " + attendanceId);
    }
}