package com.vasnhtiwari11.labour_marketplace_backend.repository;

import com.vasnhtiwari11.labour_marketplace_backend.model.Application;
import com.vasnhtiwari11.labour_marketplace_backend.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByJobId(Long jobId);

    List<Application> findByWorkerId(Long workerId);

    boolean existsByJobIdAndWorkerId(Long jobId, Long workerId);

    long countByJobIdAndStatus(Long jobId, ApplicationStatus status);
}