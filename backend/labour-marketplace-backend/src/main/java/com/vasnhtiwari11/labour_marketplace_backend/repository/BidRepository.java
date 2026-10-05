package com.vasnhtiwari11.labour_marketplace_backend.repository;

import com.vasnhtiwari11.labour_marketplace_backend.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByApplicationIdOrderByCreatedAtAsc(Long applicationId);

    Optional<Bid> findTopByApplicationIdOrderByCreatedAtDesc(Long applicationId);
}