package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.AlreadyCheckedInException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.ApplicationNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.NoActiveCheckInException;
import com.vasnhtiwari11.labour_marketplace_backend.model.Attendance;
import com.vasnhtiwari11.labour_marketplace_backend.exception.AttendanceNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn(
            @RequestParam Long applicationId,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        try {
            Attendance attendance = attendanceService.checkIn(applicationId, latitude, longitude);
            return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
        } catch (ApplicationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AlreadyCheckedInException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/check-out")
    public ResponseEntity<?> checkOut(@RequestParam Long applicationId) {
        try {
            return ResponseEntity.ok(attendanceService.checkOut(applicationId));
        } catch (NoActiveCheckInException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{applicationId}")
    public List<Attendance> getAttendanceHistory(@PathVariable Long applicationId) {
        return attendanceService.getAttendanceHistory(applicationId);
    }
    @GetMapping("/{attendanceId}/pay")
public ResponseEntity<?> calculatePay(@PathVariable Long attendanceId) {
    try {
        return ResponseEntity.ok(attendanceService.calculatePay(attendanceId));
    } catch (AttendanceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (NoActiveCheckInException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
}