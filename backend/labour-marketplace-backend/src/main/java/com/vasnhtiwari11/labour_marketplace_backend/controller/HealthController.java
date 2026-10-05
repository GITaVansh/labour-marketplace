package com.vasnhtiwari11.labour_marketplace_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthController {
    @GetMapping("/health")
    public String checkHealth() {
        return "Labour Marketplace Backend is Running";
    }
    
}   
