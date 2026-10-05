package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.*;
import com.vasnhtiwari11.labour_marketplace_backend.model.Application;
import com.vasnhtiwari11.labour_marketplace_backend.model.ApplicationStatus;
import com.vasnhtiwari11.labour_marketplace_backend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<?> applyToJob(
            @RequestParam Long jobId,
            @RequestParam Long workerId) {
        try {
            Application application = applicationService.applyToJob(jobId, workerId);
            return ResponseEntity.status(HttpStatus.CREATED).body(application);
        } catch (JobNotFoundException | WorkerProfileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DuplicateApplicationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<?> getApplicationsForJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsForJob(jobId));
    }

    @GetMapping("/worker/{workerId}")
    public ResponseEntity<?> getApplicationsForWorker(@PathVariable Long workerId) {
        return ResponseEntity.ok(applicationService.getApplicationsForWorker(workerId));
    }

    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<?> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam ApplicationStatus status) {
        try {
            Application updated = applicationService.updateApplicationStatus(applicationId, status);
            return ResponseEntity.ok(updated);
        } catch (ApplicationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}