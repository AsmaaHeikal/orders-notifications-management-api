package com.example.orders_notifications_api.dto;

import com.example.orders_notifications_api.types.*;
import lombok.Data;

@Data
public class NotificationRequestDTO {
    private NotificationType notificationType;
    private String recipient;
    private NotificationChannel channel;
    private NotificationStatus status;
    private String subject;
    private Language language;
    private Object[] messageArgs;
}
