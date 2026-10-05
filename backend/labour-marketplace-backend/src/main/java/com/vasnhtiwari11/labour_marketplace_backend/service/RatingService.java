package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.vasnhtiwari11.labour_marketplace_backend.exception.ApplicationNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.DuplicateRatingException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.InvalidRatingScoreException;
import com.vasnhtiwari11.labour_marketplace_backend.model.*;
import com.vasnhtiwari11.labour_marketplace_backend.repository.ApplicationRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public Rating submitRating(Long applicationId, BidProposedBy ratedBy, Integer score, String comment) {
        if (score < 1 || score > 5) {
            throw new InvalidRatingScoreException();
        }

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(applicationId));

        if (application.getStatus() != ApplicationStatus.ACCEPTED) {
            throw new IllegalStateException("Cannot rate an application that isn't accepted/completed.");
        }

        if (ratingRepository.existsByApplicationIdAndRatedBy(applicationId, ratedBy)) {
            throw new DuplicateRatingException(applicationId);
        }

        Rating rating = new Rating(application, ratedBy, score, comment);
        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingsForApplication(Long applicationId) {
        return ratingRepository.findByApplicationId(applicationId);
    }

    public Double getAverageRatingForWorker(Long workerId) {
        Double average = ratingRepository.findAverageRatingForWorker(workerId);
        return average != null ? average : 0.0;
    }
}