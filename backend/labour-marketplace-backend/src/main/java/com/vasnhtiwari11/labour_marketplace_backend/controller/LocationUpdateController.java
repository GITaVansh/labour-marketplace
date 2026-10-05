package com.vasnhtiwari11.labour_marketplace_backend.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LocationUpdateController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/job/{jobId}/location")
    public void updateLocation(@DestinationVariable Long jobId, LocationUpdate update) {
        messagingTemplate.convertAndSend("/topic/job/" + jobId + "/location", update);
    }

    public static class LocationUpdate {
        private Double latitude;
        private Double longitude;

        public Double getLatitude() { return latitude; }
        public void setLatitude(Double latitude) { this.latitude = latitude; }
        public Double getLongitude() { return longitude; }
        public void setLongitude(Double longitude) { this.longitude = longitude; }
    }
}