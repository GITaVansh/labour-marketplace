package com.vasnhtiwari11.labour_marketplace_backend.controller;

import com.vasnhtiwari11.labour_marketplace_backend.exception.SosAlertNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.exception.WorkerProfileNotFoundException;
import com.vasnhtiwari11.labour_marketplace_backend.model.SosAlert;
import com.vasnhtiwari11.labour_marketplace_backend.service.SosAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sos")
public class SosAlertController {

    @Autowired
    private SosAlertService sosAlertService;

    @PostMapping
    public ResponseEntity<?> triggerSos(
            @RequestParam Long workerProfileId,
            @RequestParam(required = false) Long jobId,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        try {
            SosAlert alert = sosAlertService.triggerSos(workerProfileId, jobId, latitude, longitude);
            return ResponseEntity.status(HttpStatus.CREATED).body(alert);
        } catch (WorkerProfileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{alertId}/resolve")
    public ResponseEntity<?> resolveAlert(@PathVariable Long alertId) {
        try {
            return ResponseEntity.ok(sosAlertService.resolveAlert(alertId));
        } catch (SosAlertNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/active")
    public List<SosAlert> getActiveAlerts() {
        return sosAlertService.getActiveAlerts();
    }
}