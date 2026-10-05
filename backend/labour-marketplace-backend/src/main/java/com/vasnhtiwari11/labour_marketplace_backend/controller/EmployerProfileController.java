package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.InvalidRoleException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.ProfileAlreadyExistsException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.UserNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.model.EmployerProfile;
import com.vasnhtiwari11.labour_marketplace_backend.service.EmployerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employers")
public class EmployerProfileController {

    @Autowired
    private EmployerProfileService employerProfileService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> createEmployerProfile(
            @PathVariable Long userId,
            @RequestBody EmployerProfile profile) {
        try {
            EmployerProfile saved = employerProfileService.createEmployerProfile(userId, profile);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidRoleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ProfileAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}