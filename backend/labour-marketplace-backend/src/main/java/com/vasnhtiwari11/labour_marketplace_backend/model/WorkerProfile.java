package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="worker_profiles")

public class WorkerProfile{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName="id", unique=true, nullable=false)
    private User user;

    @Column(nullable = false)
    private String primarySkill;
    private Integer experienceYears;
    private Double expectedDailyWage;
    private Integer preferredWorkRadiusKm;

    
    private String emergencyContactNumber;


    public WorkerProfile(){

    }

    public WorkerProfile(User user, String primarySkill, Integer experienceYears,
                          Double expectedDailyWage, Integer preferredWorkRadiusKm) {
        this.user = user;
        this.primarySkill = primarySkill;
        this.experienceYears = experienceYears;
        this.expectedDailyWage = expectedDailyWage;
        this.preferredWorkRadiusKm = preferredWorkRadiusKm;
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

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Double getExpectedDailyWage() {
        return expectedDailyWage;
    }

    public void setExpectedDailyWage(Double expectedDailyWage) {
        this.expectedDailyWage = expectedDailyWage;
    }

    public String getEmergencyContactNumber() {
    return emergencyContactNumber;
}

public void setEmergencyContactNumber(String emergencyContactNumber) {
    this.emergencyContactNumber = emergencyContactNumber;
}
    public Integer getPreferredWorkRadiusKm() {
        return preferredWorkRadiusKm;
    }

    public void setPreferredWorkRadiusKm(Integer preferredWorkRadiusKm) {
        this.preferredWorkRadiusKm = preferredWorkRadiusKm;
    }


}