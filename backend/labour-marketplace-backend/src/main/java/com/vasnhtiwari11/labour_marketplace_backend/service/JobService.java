package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.vasnhtiwari11.labour_marketplace_backend.exception.EmployerProfileNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.JobNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.UserNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.model.EmployerProfile;
import com.vasnhtiwari11.labour_marketplace_backend.model.Job;
import com.vasnhtiwari11.labour_marketplace_backend.model.JobStatus;
import com.vasnhtiwari11.labour_marketplace_backend.repository.EmployerProfileRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerProfileRepository employerProfileRepository;

    public Job createJob(Long employerProfileId, Job incomingJob) {
        EmployerProfile employer = employerProfileRepository.findById(employerProfileId)
        .orElseThrow(() -> new EmployerProfileNotFoundException(employerProfileId));

        incomingJob.setEmployer(employer);
        incomingJob.setStatus(JobStatus.OPEN);
        return jobRepository.save(incomingJob);
    }

    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(jobId));
    }

    public Iterable<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job updateJob(Long jobId, Job updatedFields) {
        Job existingJob = getJobById(jobId);

        existingJob.setTitle(updatedFields.getTitle());
        existingJob.setDescription(updatedFields.getDescription());
        existingJob.setLocation(updatedFields.getLocation());
        existingJob.setRequiredSkill(updatedFields.getRequiredSkill());
        existingJob.setRequiredWorkers(updatedFields.getRequiredWorkers());
        existingJob.setDailyWage(updatedFields.getDailyWage());
        existingJob.setStartDate(updatedFields.getStartDate());
        existingJob.setDurationDays(updatedFields.getDurationDays());
        existingJob.setFoodIncluded(updatedFields.getFoodIncluded());
        existingJob.setAccommodationIncluded(updatedFields.getAccommodationIncluded());
        existingJob.setMaterialsIncluded(updatedFields.getMaterialsIncluded());

        return jobRepository.save(existingJob);
    }

    public Job cancelJob(Long jobId) {
        Job existingJob = getJobById(jobId);
        existingJob.setStatus(JobStatus.CANCELLED);
        return jobRepository.save(existingJob);
    }
    public List<Job> searchJobs(String skill, String location, Double minWage, Double maxWage) {
    return jobRepository.searchJobs(skill, location, minWage, maxWage);
}
}