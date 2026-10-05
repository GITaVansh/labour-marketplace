package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.vasnhtiwari11.labour_marketplace_backend.exception.InvalidOtpException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.UserNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.model.OtpVerification;
import com.vasnhtiwari11.labour_marketplace_backend.model.User;
import com.vasnhtiwari11.labour_marketplace_backend.repository.OtpRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.UserRepository;
import com.vasnhtiwari11.labour_marketplace_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final SecureRandom secureRandom = new SecureRandom();

    public void generateAndSendOtp(String phoneNumber) {
        String code = String.format("%06d", secureRandom.nextInt(1000000));
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);

        OtpVerification otp = new OtpVerification(phoneNumber, code, expiresAt);
        otpRepository.save(otp);

        System.out.println("=== OTP for " + phoneNumber + ": " + code + " ===");
    }

    public String verifyOtp(String phoneNumber, String submittedCode) {
        OtpVerification otp = otpRepository
                .findTopByPhoneNumberAndUsedFalseOrderByIdDesc(phoneNumber)
                .orElseThrow(() -> new InvalidOtpException("No OTP request found for this phone number."));

        if (LocalDateTime.now().isAfter(otp.getExpiresAt())) {
            throw new InvalidOtpException("OTP has expired. Please request a new one.");
        }

        if (!otp.getCode().equals(submittedCode)) {
            throw new InvalidOtpException("Incorrect OTP.");
        }

        otp.setUsed(true);
        otpRepository.save(otp);

        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException(phoneNumber));

        return jwtUtil.generateToken(user.getId(), user.getRole().name());
    }
}