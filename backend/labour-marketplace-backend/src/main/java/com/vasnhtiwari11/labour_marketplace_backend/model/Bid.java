package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id", nullable = false)
    private Application application;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BidProposedBy proposedBy;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BidStatus status = BidStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Bid() {
    }

    public Bid(Application application, BidProposedBy proposedBy, Double amount) {
        this.application = application;
        this.proposedBy = proposedBy;
        this.amount = amount;
        this.status = BidStatus.PENDING;
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

    public BidProposedBy getProposedBy() {
        return proposedBy;
    }

    public void setProposedBy(BidProposedBy proposedBy) {
        this.proposedBy = proposedBy;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BidStatus getStatus() {
        return status;
    }

    public void setStatus(BidStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}