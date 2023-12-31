package com.example.orders_notifications_api.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import com.example.orders_notifications_api.models.Notification;
import com.example.orders_notifications_api.models.Product;
import com.example.orders_notifications_api.templates.NotificationTemplate;
import com.example.orders_notifications_api.types.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StatisticsService {

    public void updateStat(NotificationService notificationService){
        String Email = MostNotifiedEmail(notificationService);
        String Template = MostSentNotificationTemplate(notificationService);
    }
    public String MostSentNotificationTemplate(NotificationService notificationService){
        String  mostRepeated= null;
        int cancel=0,place=0,ship=0;
        Map<String, Integer> frequencyMap = new HashMap<>();
        for(Notification n : notificationService.AllNotifications){
            if(n.getType() == NotificationType.ORDER_CANCELLATION ){
                cancel++;
            }else if(n.getType() == NotificationType.ORDER_PLACEMENT ){
                place++;
            }else if(n.getType() == NotificationType.ORDER_SHIPMENT ){
                ship++;
            }
            if(ship>place){
                if(ship>cancel){
                    mostRepeated=NotificationType.ORDER_SHIPMENT.toLower();
                }else if(ship==cancel) mostRepeated=NotificationType.ORDER_CANCELLATION.toLower()+" "+NotificationType.ORDER_SHIPMENT.toLower();
                else mostRepeated=NotificationType.ORDER_CANCELLATION.toLower();
            }else if(place>ship){
                if(place>cancel){
                    mostRepeated=NotificationType.ORDER_PLACEMENT.toLower();
                }else if(place==cancel) mostRepeated=NotificationType.ORDER_CANCELLATION.toLower()+" "+NotificationType.ORDER_PLACEMENT.toLower();
                else mostRepeated=NotificationType.ORDER_CANCELLATION.toLower();
            }else if (place==ship) mostRepeated=NotificationType.ORDER_SHIPMENT.toLower()+" "+NotificationType.ORDER_PLACEMENT.toLower();
            else mostRepeated=NotificationType.ORDER_CANCELLATION.toLower()+" "+NotificationType.ORDER_PLACEMENT.toLower()+" "+NotificationType.ORDER_PLACEMENT.toLower();

        }
        return mostRepeated;
    }

    public String MostNotifiedEmail(NotificationService notificationService){
        String  mostRepeated= null;
        int maxFreq = 0;
        Map<String, Integer> frequencyMap = new HashMap<>();
        for(Notification n : notificationService.AllNotifications){
            if(n.getChannel().toLower().equals("email")){
                int freq = frequencyMap.getOrDefault(n.getRecipient(), 0);
                frequencyMap.put(n.getRecipient(), freq);

                if (freq > maxFreq) {
                    maxFreq = freq;
                    mostRepeated = n.getRecipient();
                }
                return mostRepeated;
            }
        }
        return mostRepeated;
    }
    public ArrayList<Notification> getAllNotifications(NotificationService notificationService){
        return notificationService.getAllNotifications();
    }
    public Queue<Notification> getNotificationQueue(NotificationService notificationService){
        return notificationService.getNotificationQueue();
    }


}
