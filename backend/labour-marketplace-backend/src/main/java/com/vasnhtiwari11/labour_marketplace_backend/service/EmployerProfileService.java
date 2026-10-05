package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.vasnhtiwari11.labour_marketplace_backend.exception.*;
import com.vasnhtiwari11.labour_marketplace_backend.model.*;
import com.vasnhtiwari11.labour_marketplace_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EmployerProfileService {

    @Autowired
    private EmployerProfileRepository employerProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public EmployerProfile createEmployerProfile(Long userId, EmployerProfile incomingProfile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.getRole() != UserRole.EMPLOYER) {
            throw new InvalidRoleException(
                "User " + userId + " is registered as " + user.getRole() + ", not EMPLOYER."
            );
        }

        incomingProfile.setUser(user);

        try {
            return employerProfileRepository.save(incomingProfile);
        } catch (DataIntegrityViolationException e) {
            throw new ProfileAlreadyExistsException(userId);
        }
    }
}