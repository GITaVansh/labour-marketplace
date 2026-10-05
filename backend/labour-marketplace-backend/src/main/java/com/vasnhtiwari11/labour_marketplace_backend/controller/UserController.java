package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.model.User;
import com.vasnhtiwari11.labour_marketplace_backend.exception.DuplicatePhoneNumberException;
import com.vasnhtiwari11.labour_marketplace_backend.repository.UserRepository;
import com.vasnhtiwari11.labour_marketplace_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (DuplicatePhoneNumberException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }
}