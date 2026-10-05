package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.*;
import com.vasnhtiwari11.labour_marketplace_backend.model.BidProposedBy;
import com.vasnhtiwari11.labour_marketplace_backend.model.Rating;
import com.vasnhtiwari11.labour_marketplace_backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> submitRating(
            @RequestParam Long applicationId,
            @RequestParam BidProposedBy ratedBy,
            @RequestParam Integer score,
            @RequestParam(required = false) String comment) {
        try {
            Rating rating = ratingService.submitRating(applicationId, ratedBy, score, comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(rating);
        } catch (InvalidRatingScoreException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ApplicationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DuplicateRatingException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/application/{applicationId}")
    public List<Rating> getRatingsForApplication(@PathVariable Long applicationId) {
        return ratingService.getRatingsForApplication(applicationId);
    }

    @GetMapping("/worker/{workerId}/average")
    public ResponseEntity<?> getAverageRatingForWorker(@PathVariable Long workerId) {
        return ResponseEntity.ok(java.util.Map.of("workerId", workerId, "averageRating", ratingService.getAverageRatingForWorker(workerId)));
    }
}