package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.EmployerProfileNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.JobNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.UserNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.model.Job;
import com.vasnhtiwari11.labour_marketplace_backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/{employerProfileId}")
    public ResponseEntity<?> createJob(
            @PathVariable Long employerProfileId,
            @RequestBody Job job) {
        try {
            Job saved = jobService.createJob(employerProfileId, job);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public Iterable<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<?> getJobById(@PathVariable Long jobId) {
        try {
            return ResponseEntity.ok(jobService.getJobById(jobId));
        } catch (JobNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<?> updateJob(
            @PathVariable Long jobId,
            @RequestBody Job job) {
        try {
            return ResponseEntity.ok(jobService.updateJob(jobId, job));
        } catch (JobNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<?> cancelJob(@PathVariable Long jobId) {
        try {
            return ResponseEntity.ok(jobService.cancelJob(jobId));
        } catch (EmployerProfileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchJobs(
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minWage,
            @RequestParam(required = false) Double maxWage) {
        return ResponseEntity.ok(jobService.searchJobs(skill, location, minWage, maxWage));
    }
}