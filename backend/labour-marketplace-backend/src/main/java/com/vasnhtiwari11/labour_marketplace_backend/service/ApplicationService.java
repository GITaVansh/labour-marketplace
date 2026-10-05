package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.vasnhtiwari11.labour_marketplace_backend.exception.ApplicationNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.DuplicateApplicationException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.JobNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.UserNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.WorkerProfileNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.model.*;
import com.vasnhtiwari11.labour_marketplace_backend.repository.ApplicationRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.JobRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.WorkerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    public Application applyToJob(Long jobId, Long workerId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(jobId));

        WorkerProfile worker = workerProfileRepository.findById(workerId)
        .orElseThrow(() -> new WorkerProfileNotFoundException(workerId));

        if (applicationRepository.existsByJobIdAndWorkerId(jobId, workerId)) {
            throw new DuplicateApplicationException(jobId, workerId);
        }

        Application application = new Application(job, worker);
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsForJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    public List<Application> getApplicationsForWorker(Long workerId) {
        return applicationRepository.findByWorkerId(workerId);
    }

    public Application updateApplicationStatus(Long applicationId, ApplicationStatus newStatus) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException(applicationId));

        application.setStatus(newStatus);
        return applicationRepository.save(application);
    }
}