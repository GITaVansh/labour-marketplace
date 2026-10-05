package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sos_alerts")
public class SosAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "worker_profile_id", referencedColumnName = "id", nullable = false)
    private WorkerProfile worker;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private LocalDateTime triggeredAt = LocalDateTime.now();

    private Boolean resolved = false;

    public SosAlert() {
    }

    public SosAlert(WorkerProfile worker, Job job, Double latitude, Double longitude) {
        this.worker = worker;
        this.job = job;
        this.latitude = latitude;
        this.longitude = longitude;
        this.resolved = false;
    }

    public Long getId() {
        return id;
    }

    public WorkerProfile getWorker() {
        return worker;
    }

    public void setWorker(WorkerProfile worker) {
        this.worker = worker;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTriggeredAt() {
        return triggeredAt;
    }

    public void setTriggeredAt(LocalDateTime triggeredAt) {
        this.triggeredAt = triggeredAt;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}