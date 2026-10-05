package com.vasnhtiwari11.labour_marketplace_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id", nullable = false)
    private Application application;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BidProposedBy ratedBy;

    @Column(nullable = false)
    private Integer score;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Rating() {
    }

    public Rating(Application application, BidProposedBy ratedBy, Integer score, String comment) {
        this.application = application;
        this.ratedBy = ratedBy;
        this.score = score;
        this.comment = comment;
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

    public BidProposedBy getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(BidProposedBy ratedBy) {
        this.ratedBy = ratedBy;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}