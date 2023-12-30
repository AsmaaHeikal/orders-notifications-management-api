package com.example.orders_notifications_api.templates;

import com.example.orders_notifications_api.models.Notification;
import com.example.orders_notifications_api.types.*;


public abstract class NotificationTemplate {

    public final Notification createNotification(
            String recipient,
            Language language,
            NotificationChannel channel,
            NotificationType type,
            NotificationStatus status,
            String subject,
            Object... args) {

        Notification notification = new Notification();
        notification.setRecipient(recipient);
        notification.setLanguage(language);
        notification.setChannel(channel);
        notification.setType(type);
        notification.setStatus(status);
        notification.setSubject(subject);
        notification.setMessage(constructMessage(args));
        return notification;
    }

    protected abstract String constructMessage(Object... args);
}
