package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id", nullable = false)
    private Application application;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, unique = true)
    private String razorpayOrderId;

    private String razorpayPaymentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.CREATED;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Payment() {
    }

    public Payment(Application application, Double amount, String razorpayOrderId) {
        this.application = application;
        this.amount = amount;
        this.razorpayOrderId = razorpayOrderId;
        this.status = PaymentStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}