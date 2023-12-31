package com.example.orders_notifications_api.services;

import java.util.HashMap;
import java.util.Map;

import com.example.orders_notifications_api.models.Notification;
import org.springframework.stereotype.Service;
@Service
public class StatisticsService {

    public void updateStat(NotificationService n){
        String Email = MostNotifiedEmail(n);
        String Template = MostSentNotificationTemplate(n);
    }
    public String MostSentNotificationTemplate(NotificationService notify){
        String  mostRepeated= null;
        int maxFreq = 0;
        Map<String, Integer> frequencyMap = new HashMap<>();
        for(Notification n : notify.AllNotifications){
            int freq = frequencyMap.getOrDefault(n.getType().toLower(),0);
            frequencyMap.put(n.getType().toLower(),freq);

            if(freq>maxFreq){
                maxFreq = freq;
                mostRepeated = n.getType().toLower();
            }
            return mostRepeated;
        }
        return null;
    }

    public String MostNotifiedEmail(NotificationService notify){
        String  mostRepeated= null;
        int maxFreq = 0;
        Map<String, Integer> frequencyMap = new HashMap<>();
        for(Notification n : notify.AllNotifications){
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
        return null;
    }

}
