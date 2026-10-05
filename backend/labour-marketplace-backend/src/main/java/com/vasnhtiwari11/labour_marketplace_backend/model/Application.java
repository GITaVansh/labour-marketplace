package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="applications")

public class Application {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "id", nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name = "worker_profile_id", referencedColumnName = "id", nullable = false)
    private WorkerProfile worker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime appliedAt = LocalDateTime.now();

    public Application() {
    }

    public Application(Job job, WorkerProfile worker) {
        this.job = job;
        this.worker = worker;
    }

    public Long getId() {
        return id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public WorkerProfile getWorker() {
        return worker;
    }

    public void setWorker(WorkerProfile worker) {
        this.worker = worker;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    } 

}
