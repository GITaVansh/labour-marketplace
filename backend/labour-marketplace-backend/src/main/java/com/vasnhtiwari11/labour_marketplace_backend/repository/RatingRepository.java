package com.vasnhtiwari11.labour_marketplace_backend.repository;

import com.vasnhtiwari11.labour_marketplace_backend.model.BidProposedBy;
import com.vasnhtiwari11.labour_marketplace_backend.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    boolean existsByApplicationIdAndRatedBy(Long applicationId, BidProposedBy ratedBy);

    List<Rating> findByApplicationId(Long applicationId);

    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.application.worker.id = :workerId AND r.ratedBy = 'EMPLOYER'")
    Double findAverageRatingForWorker(@Param("workerId") Long workerId);
}