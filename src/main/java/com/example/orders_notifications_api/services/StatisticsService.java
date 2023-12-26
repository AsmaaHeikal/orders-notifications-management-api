package com.example.orders_notifications_api.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class StatisticsService {
    private Map<String, Integer> emailCounter = new HashMap<>();
    private Map<String, Integer> templateCounter = new HashMap<>();

    public void incrementEmailCounter(String emailAddress) {
        emailCounter.put(emailAddress, emailCounter.getOrDefault(emailAddress, 0) + 1);
    }
    public void incrementTemplateCounter(String templateName) {
        templateCounter.put(templateName, templateCounter.getOrDefault(templateName, 0) + 1);
    }
    public Map<String, Integer> getEmailStatistics() {
        return emailCounter;
    }
    public Map<String, Integer> getTemplateStatistics() {
        return templateCounter;
    }
}
