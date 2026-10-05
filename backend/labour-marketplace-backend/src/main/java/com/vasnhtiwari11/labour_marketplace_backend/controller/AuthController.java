package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.InvalidOtpException;
import com.vasnhtiwari11.labour_marketplace_backend.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/otp/request")
    public ResponseEntity<?> requestOtp(@RequestParam String phoneNumber) {
        otpService.generateAndSendOtp(phoneNumber);
        return ResponseEntity.ok("OTP sent successfully.");
    }

    @PostMapping("/otp/verify")
    public ResponseEntity<?> verifyOtp(
            @RequestParam String phoneNumber,
            @RequestParam String code) {
        try {
            String token = otpService.verifyOtp(phoneNumber, code);
            return ResponseEntity.ok(java.util.Map.of("token", token));
        } catch (InvalidOtpException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}