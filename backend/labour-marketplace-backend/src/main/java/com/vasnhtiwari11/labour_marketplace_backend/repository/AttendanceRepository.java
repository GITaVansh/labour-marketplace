package com.vasnhtiwari11.labour_marketplace_backend.repository;

import com.vasnhtiwari11.labour_marketplace_backend.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByApplicationIdOrderByCheckInTimeDesc(Long applicationId);

    Optional<Attendance> findTopByApplicationIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(Long applicationId);
}