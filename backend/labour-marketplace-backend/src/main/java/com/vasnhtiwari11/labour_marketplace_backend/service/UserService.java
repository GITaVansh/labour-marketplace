package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.vasnhtiwari11.labour_marketplace_backend.exception.DuplicatePhoneNumberException;
import com.vasnhtiwari11.labour_marketplace_backend.model.User;
import com.vasnhtiwari11.labour_marketplace_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatePhoneNumberException(user.getPhoneNumber());
        }
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}