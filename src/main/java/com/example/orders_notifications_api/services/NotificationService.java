package com.example.orders_notifications_api.services;

import java.time.LocalDateTime;
import java.util.*;

import com.example.orders_notifications_api.models.common.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.orders_notifications_api.factories.NotificationTemplateFactory;
import com.example.orders_notifications_api.models.Notification;
import com.example.orders_notifications_api.templates.NotificationTemplate;
import com.example.orders_notifications_api.types.*;

@Service
public class NotificationService {
@Autowired
    StatisticsService stat = new StatisticsService();

    public Queue<Notification> getNotificationQueue() {
        return notificationQueue;
    }

    public ArrayList<Notification> getAllNotifications() {
        return AllNotifications;
    }

    private final Queue<Notification> notificationQueue = new LinkedList<>();

    ArrayList<Notification> AllNotifications = new ArrayList<>();

    private void notifyStat(){
        stat.updateStat(this);
    }

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
        AllNotifications.add(notification);
        notifyStat();

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
        return notification;
    }

    private void enqueueNotification(Notification notification) {
        notificationQueue.add(notification);
    }
    private void dequeueNotification(Notification notification) {
        notificationQueue.remove(notification);
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

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime notifyTime = notification.getPlacementTime().plus(notification.NOTIFICATION_DELAY);
        if(notifyTime.isAfter(currentTime)){
            System.out.println("Sending notification: " + notification);
            notification.setStatus(NotificationStatus.SENT);
            dequeueNotification(notification);
        }
    }

}