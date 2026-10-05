package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.*;
import com.vasnhtiwari11.labour_marketplace_backend.model.Payment;
import com.vasnhtiwari11.labour_marketplace_backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(
            @RequestParam Long applicationId,
            @RequestParam Double amount) {
        try {
            Payment payment = paymentService.createPaymentOrder(applicationId, amount);
            return ResponseEntity.status(HttpStatus.CREATED).body(payment);
        } catch (ApplicationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PaymentVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(
            @RequestParam String razorpayOrderId,
            @RequestParam String razorpayPaymentId,
            @RequestParam String razorpaySignature) {
        try {
            Payment payment = paymentService.verifyAndHoldPayment(razorpayOrderId, razorpayPaymentId, razorpaySignature);
            return ResponseEntity.ok(payment);
        } catch (PaymentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PaymentVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{paymentId}/release")
    public ResponseEntity<?> releasePayment(@PathVariable Long paymentId) {
        try {
            return ResponseEntity.ok(paymentService.releasePayment(paymentId));
        } catch (PaymentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PaymentVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}