package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.model.JobStatus;
import com.vasnhtiwari11.labour_marketplace_backend.model.PaymentStatus;
import com.vasnhtiwari11.labour_marketplace_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SosAlertRepository sosAlertRepository;

    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new LinkedHashMap<>();

        long totalUsers = userRepository.count();
        long totalJobs = jobRepository.count();
        long openJobs = jobRepository.findAll().stream()
                .filter(job -> job.getStatus() == JobStatus.OPEN)
                .count();
        long totalApplications = applicationRepository.count();

        double totalHeldPayments = paymentRepository.findAll().stream()
                .filter(p -> p.getStatus() == PaymentStatus.HELD)
                .mapToDouble(p -> p.getAmount())
                .sum();

        double totalReleasedPayments = paymentRepository.findAll().stream()
                .filter(p -> p.getStatus() == PaymentStatus.RELEASED)
                .mapToDouble(p -> p.getAmount())
                .sum();

        long activeSosAlerts = sosAlertRepository.findByResolvedFalse().size();

        stats.put("totalUsers", totalUsers);
        stats.put("totalJobs", totalJobs);
        stats.put("openJobs", openJobs);
        stats.put("totalApplications", totalApplications);
        stats.put("totalHeldPayments", totalHeldPayments);
        stats.put("totalReleasedPayments", totalReleasedPayments);
        stats.put("activeSosAlerts", activeSosAlerts);

        return stats;
    }
}