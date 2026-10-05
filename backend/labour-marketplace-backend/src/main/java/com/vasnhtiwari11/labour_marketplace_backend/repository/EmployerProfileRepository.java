package com.vasnhtiwari11.labour_marketplace_backend.repository;

import com.vasnhtiwari11.labour_marketplace_backend.model.EmployerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerProfileRepository extends JpaRepository<EmployerProfile, Long> {
}