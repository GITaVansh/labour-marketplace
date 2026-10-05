package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id", nullable = false)
    private Application application;

    @Column(nullable = false)
    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private Double checkInLatitude;

    private Double checkInLongitude;

    public Attendance() {
    }

    public Attendance(Application application, LocalDateTime checkInTime,
                       Double checkInLatitude, Double checkInLongitude) {
        this.application = application;
        this.checkInTime = checkInTime;
        this.checkInLatitude = checkInLatitude;
        this.checkInLongitude = checkInLongitude;
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

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Double getCheckInLatitude() {
        return checkInLatitude;
    }

    public void setCheckInLatitude(Double checkInLatitude) {
        this.checkInLatitude = checkInLatitude;
    }

    public Double getCheckInLongitude() {
        return checkInLongitude;
    }

    public void setCheckInLongitude(Double checkInLongitude) {
        this.checkInLongitude = checkInLongitude;
    }
}