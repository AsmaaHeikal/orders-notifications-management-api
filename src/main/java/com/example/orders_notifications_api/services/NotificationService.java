package com.example.orders_notifications_api.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

import com.example.orders_notifications_api.factories.NotificationTemplateFactory;
import com.example.orders_notifications_api.models.Notification;
import com.example.orders_notifications_api.templates.NotificationTemplate;
import com.example.orders_notifications_api.types.*;

@Service
public class NotificationService {

    private final Queue<Notification> notificationQueue = new LinkedList<>();

    public Notification createNotification(
            NotificationType notificationType,
            String subject,
            String recipient,
            Language language,
            NotificationChannel channel,
            NotificationStatus status,
            Object... args) {


        if (language == null) {
            language = Language.EN;
        }

        NotificationTemplate template = NotificationTemplateFactory.getTemplate(notificationType);
        Notification notification = template.createNotification(recipient, language, channel, notificationType, status,
                subject, args);
        enqueueNotification(notification);
        return notification;
    }


    public Notification createNotification(
            NotificationType notificationType,
            String recipient,
            NotificationChannel channel,
            NotificationStatus status,
            Object... args) {
        String defaultSubject = "Incoming Notification";
        Language language = Language.EN;
        Notification notification = createNotification(notificationType, defaultSubject, recipient, language, channel,
                status, args);
        enqueueNotification(notification);
        return notification;
    }

    private void enqueueNotification(Notification notification) {
        notificationQueue.add(notification);
    }

    public List<Notification> getQueuedNotifications() {
        return new LinkedList<>(notificationQueue);
    }

    public List<Notification> getAndLogQueuedNotifications() {
        List<Notification> queuedNotifications = new LinkedList<>(notificationQueue);

        logNotifications(queuedNotifications);

        return queuedNotifications;
    }

    private void logNotifications(List<Notification> notifications) {
        if (notifications.isEmpty()) {
            System.out.println("The notification queue is empty.");
        } else {
            System.out.println("Notifications in the queue:");
            for (Notification notification : notifications) {
                System.out.println(notification);
            }
        }
    }

    public void processAndSendNotifications() {
        while (!notificationQueue.isEmpty()) {
            Notification notification = notificationQueue.poll();
            sendNotification(notification);
        }
    }

    private void sendNotification(Notification notification) {
        System.out.println("Sending notification: " + notification);
        notification.setStatus(NotificationStatus.SENT);
    }
}