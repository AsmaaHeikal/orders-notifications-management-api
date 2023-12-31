package com.example.orders_notifications_api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.example.orders_notifications_api.types.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Notification {
    private String recipient;
    private String subject;
    private String message;
    private NotificationType type;
    private Language language;
    private NotificationChannel channel;
    private NotificationStatus status;
    public static final Duration NOTIFICATION_DELAY = Duration.ofSeconds(40);

    public LocalDateTime getPlacementTime() {
        return placementTime;
    }

    protected LocalDateTime placementTime;

    public Notification() {
        this.placementTime = LocalDateTime.now();
    }

    public NotificationType getType() {
        return type;
    }





}