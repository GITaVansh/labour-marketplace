package com.vasnhtiwari11.labour_marketplace_backend.repository;

import com.vasnhtiwari11.labour_marketplace_backend.model.SosAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SosAlertRepository extends JpaRepository<SosAlert, Long> {
    List<SosAlert> findByResolvedFalse();
}