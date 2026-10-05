package com.vasnhtiwari11.labour_marketplace_backend.repository;

import com.vasnhtiwari11.labour_marketplace_backend.model.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerProfileRepository extends JpaRepository<WorkerProfile, Long> {
}