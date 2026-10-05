package com.vasnhtiwari11.labour_marketplace_backend.service;

import com.vasnhtiwari11.labour_marketplace_backend.exception.SosAlertNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.WorkerProfileNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.model.Job;
import com.vasnhtiwari11.labour_marketplace_backend.model.SosAlert;
import com.vasnhtiwari11.labour_marketplace_backend.model.WorkerProfile;
import com.vasnhtiwari11.labour_marketplace_backend.repository.JobRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.SosAlertRepository;
import com.vasnhtiwari11.labour_marketplace_backend.repository.WorkerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SosAlertService {

    @Autowired
    private SosAlertRepository sosAlertRepository;

    @Autowired
    private WorkerProfileRepository workerProfileRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public SosAlert triggerSos(Long workerProfileId, Long jobId, Double latitude, Double longitude) {
        WorkerProfile worker = workerProfileRepository.findById(workerProfileId)
                .orElseThrow(() -> new WorkerProfileNotFoundException(workerProfileId));

        Job job = null;
        if (jobId != null) {
            job = jobRepository.findById(jobId).orElse(null);
        }

        SosAlert alert = new SosAlert(worker, job, latitude, longitude);
        SosAlert savedAlert = sosAlertRepository.save(alert);

        String emergencyContact = worker.getEmergencyContactNumber();
        System.out.println("=== SOS ALERT ===");
        System.out.println("Worker: " + worker.getUser().getFullName());
        System.out.println("Location: " + latitude + ", " + longitude);
        System.out.println("Job: " + (job != null ? job.getTitle() : "N/A"));
        if (emergencyContact != null) {
            System.out.println("Would notify emergency contact: " + emergencyContact + " (SMS integration placeholder)");
        } else {
            System.out.println("No emergency contact on file for this worker.");
        }
        System.out.println("=================");

        messagingTemplate.convertAndSend("/topic/admin/sos-alerts", savedAlert);

        return savedAlert;
    }

    public SosAlert resolveAlert(Long alertId) {
        SosAlert alert = sosAlertRepository.findById(alertId)
                .orElseThrow(() -> new SosAlertNotFoundException(alertId));
        alert.setResolved(true);
        return sosAlertRepository.save(alert);
    }

    public List<SosAlert> getActiveAlerts() {
        return sosAlertRepository.findByResolvedFalse();
    }
}