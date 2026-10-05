package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.*;
import com.vasnhtiwari11.labour_marketplace_backend.model.WorkerProfile;
import com.vasnhtiwari11.labour_marketplace_backend.service.WorkerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workers")
public class WorkerProfileController {

    @Autowired
    private WorkerProfileService workerProfileService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> createWorkerProfile(
            @PathVariable Long userId,
            @RequestBody WorkerProfile profile) {
        try {
            WorkerProfile saved = workerProfileService.createWorkerProfile(userId, profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidRoleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DuplicatePhoneNumberException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    
}
