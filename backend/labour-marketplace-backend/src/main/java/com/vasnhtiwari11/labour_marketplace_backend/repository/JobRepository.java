package com.vasnhtiwari11.labour_marketplace_backend.repository;

import com.vasnhtiwari11.labour_marketplace_backend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE j.status = 'OPEN' " +
           "AND (:skill IS NULL OR j.requiredSkill = :skill) " +
           "AND (:location IS NULL OR j.location = :location) " +
           "AND (:minWage IS NULL OR j.dailyWage >= :minWage) " +
           "AND (:maxWage IS NULL OR j.dailyWage <= :maxWage)")
    List<Job> searchJobs(
            @Param("skill") String skill,
            @Param("location") String location,
            @Param("minWage") Double minWage,
            @Param("maxWage") Double maxWage
    );
}