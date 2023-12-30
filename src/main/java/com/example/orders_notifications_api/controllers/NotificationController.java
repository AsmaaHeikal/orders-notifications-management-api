package com.example.orders_notifications_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.orders_notifications_api.dto.NotificationRequestDTO;
import com.example.orders_notifications_api.models.Notification;
import com.example.orders_notifications_api.services.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // only for testing
    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(
            @RequestBody NotificationRequestDTO request) {

        Notification notification = notificationService.createNotification(
                request.getNotificationType(),
                request.getSubject(),
                request.getRecipient(),
                request.getLanguage(),
                request.getChannel(),
                request.getStatus(),
                request.getMessageArgs());
        return ResponseEntity.ok(notification);
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotifications() {
        notificationService.processAndSendNotifications();
        return ResponseEntity.ok("All queued notifications have been sent.");
    }

    @GetMapping("/queue")
    public ResponseEntity<List<Notification>> viewQueue() {
        List<Notification> queuedNotifications = notificationService.getQueuedNotifications();
        return ResponseEntity.ok(queuedNotifications);
    }
}
