package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.vasnhtiwari11.labour_marketplace_backend.exception.ApplicationNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.BidNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.JobFullyStaffedException;
import com.vasnhtiwari11.labour_marketplace_backend.model.*;
import com.vasnhtiwari11.labour_marketplace_backend.repository.ApplicationRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.BidRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private org.springframework.messaging.simp.SimpMessagingTemplate messagingTemplate;

    public Bid placeBid(Long applicationId, BidProposedBy proposedBy, Double amount) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(applicationId));

        bidRepository.findTopByApplicationIdOrderByCreatedAtDesc(applicationId)
                .filter(existing -> existing.getStatus() == BidStatus.PENDING)
                .ifPresent(existing -> {
                    existing.setStatus(BidStatus.COUNTERED);
                    bidRepository.save(existing);
                });

        Bid bid = new Bid(application, proposedBy, amount);
        return bidRepository.save(bid);
    }

    public Bid acceptLatestBid(Long applicationId) {
        Bid latestBid = bidRepository.findTopByApplicationIdOrderByCreatedAtDesc(applicationId)
                .orElseThrow(() -> new BidNotFoundException(applicationId));

        Application application = latestBid.getApplication();
        Job job = application.getJob();

        long acceptedCount = applicationRepository.countByJobIdAndStatus(job.getId(), ApplicationStatus.ACCEPTED);

        if (acceptedCount >= job.getRequiredWorkers()) {
            throw new JobFullyStaffedException(job.getId());
        }

        latestBid.setStatus(BidStatus.ACCEPTED);
        Bid savedBid = bidRepository.save(latestBid);

        application.setStatus(ApplicationStatus.ACCEPTED);
        applicationRepository.save(application);

        Long workerId = application.getWorker().getId();
        messagingTemplate.convertAndSend(
        "/topic/worker/" + workerId + "/notifications",
        "Your bid for job '" + job.getTitle() + "' was accepted at ₹" + savedBid.getAmount()
        );  

        long newAcceptedCount = acceptedCount + 1;
        if (newAcceptedCount >= job.getRequiredWorkers()) {
            job.setStatus(JobStatus.IN_PROGRESS);
            jobRepository.save(job);
        }

        return savedBid;
    }

    public List<Bid> getBidHistory(Long applicationId) {
        return bidRepository.findByApplicationIdOrderByCreatedAtAsc(applicationId);
    }
}