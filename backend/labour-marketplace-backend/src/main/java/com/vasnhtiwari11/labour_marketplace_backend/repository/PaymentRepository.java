package com.vasnhtiwari11.labour_marketplace_backend.repository;

import com.vasnhtiwari11.labour_marketplace_backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);
}