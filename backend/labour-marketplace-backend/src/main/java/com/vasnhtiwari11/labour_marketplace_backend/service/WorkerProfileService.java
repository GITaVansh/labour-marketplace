package com.vasnhtiwari11.labour_marketplace_backend.service;


import com.vasnhtiwari11.labour_marketplace_backend.exception.*;
import com.vasnhtiwari11.labour_marketplace_backend.model.*;
import com.vasnhtiwari11.labour_marketplace_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class WorkerProfileService {
    
    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public WorkerProfile createWorkerProfile(Long userId, WorkerProfile incomingProfile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.getRole() != UserRole.WORKER) {
            throw new InvalidRoleException(
                "User " + userId + " is registered as " + user.getRole() + ", not WORKER."
            );
        }

        incomingProfile.setUser(user);

        try {
            return workerProfileRepository.save(incomingProfile);
        }  catch (DataIntegrityViolationException e) {
                throw new ProfileAlreadyExistsException(userId);
            }
    }
}
