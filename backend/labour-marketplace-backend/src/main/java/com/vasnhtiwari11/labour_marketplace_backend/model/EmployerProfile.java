package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;


@Entity
@Table(name="employer_profiles")
public class EmployerProfile {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    private User user;

    private String businessName;

    @Column(nullable = false)
    private Boolean verified = false;

    public EmployerProfile() {
    }

     public EmployerProfile(User user, String businessName) {
        this.user = user;
        this.businessName = businessName;
        this.verified = false;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}

