package com.example.orders_notifications_api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.example.orders_notifications_api.types.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String recipient;
    private String subject;
    private String message;
    private NotificationType type;
    private Language language;
    private NotificationChannel channel;
    private NotificationStatus status;
}