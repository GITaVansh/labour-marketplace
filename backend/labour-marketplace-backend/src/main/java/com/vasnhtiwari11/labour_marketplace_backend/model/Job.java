package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "jobs")
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employer_profile_id", referencedColumnName = "id", nullable = false)
    private EmployerProfile employer;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String requiredSkill;

    @Column(nullable = false)
    private Integer requiredWorkers;

    @Column(nullable = false)
    private Double dailyWage;

    private LocalDate startDate;

    private Integer durationDays;

    private Boolean foodIncluded = false;

    private Boolean accommodationIncluded = false;

    private Boolean materialsIncluded = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status = JobStatus.OPEN;

    public Job() {
    }

    public Job(EmployerProfile employer, String title, String description, String location,
                String requiredSkill, Integer requiredWorkers, Double dailyWage,
                LocalDate startDate, Integer durationDays) {
        this.employer = employer;
        this.title = title;
        this.description = description;
        this.location = location;
        this.requiredSkill = requiredSkill;
        this.requiredWorkers = requiredWorkers;
        this.dailyWage = dailyWage;
        this.startDate = startDate;
        this.durationDays = durationDays;
    }

    public Long getId() {
        return id;
    }

    public EmployerProfile getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerProfile employer) {
        this.employer = employer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public Integer getRequiredWorkers() {
        return requiredWorkers;
    }

    public void setRequiredWorkers(Integer requiredWorkers) {
        this.requiredWorkers = requiredWorkers;
    }

    public Double getDailyWage() {
        return dailyWage;
    }

    public void setDailyWage(Double dailyWage) {
        this.dailyWage = dailyWage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public Boolean getFoodIncluded() {
        return foodIncluded;
    }

    public void setFoodIncluded(Boolean foodIncluded) {
        this.foodIncluded = foodIncluded;
    }

    public Boolean getAccommodationIncluded() {
        return accommodationIncluded;
    }

    public void setAccommodationIncluded(Boolean accommodationIncluded) {
        this.accommodationIncluded = accommodationIncluded;
    }

    public Boolean getMaterialsIncluded() {
        return materialsIncluded;
    }

    public void setMaterialsIncluded(Boolean materialsIncluded) {
        this.materialsIncluded = materialsIncluded;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}
