package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.ApplicationNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.BidNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.JobFullyStaffedException;
import com.vasnhtiwari11.labour_marketplace_backend.model.Bid;
import com.vasnhtiwari11.labour_marketplace_backend.model.BidProposedBy;
import com.vasnhtiwari11.labour_marketplace_backend.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @PostMapping
    public ResponseEntity<?> placeBid(
            @RequestParam Long applicationId,
            @RequestParam BidProposedBy proposedBy,
            @RequestParam Double amount) {
        try {
            Bid bid = bidService.placeBid(applicationId, proposedBy, amount);
            return ResponseEntity.status(HttpStatus.CREATED).body(bid);
        } catch (ApplicationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/application/{applicationId}/accept-latest")
    public ResponseEntity<?> acceptLatestBid(@PathVariable Long applicationId) {
        try {
            return ResponseEntity.ok(bidService.acceptLatestBid(applicationId));
        } catch (BidNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (JobFullyStaffedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/application/{applicationId}")
    public List<Bid> getBidHistory(@PathVariable Long applicationId) {
        return bidService.getBidHistory(applicationId);
    }
}