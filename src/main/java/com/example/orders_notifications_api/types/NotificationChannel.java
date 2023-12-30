package com.example.orders_notifications_api.types;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationChannel {
    EMAIL,
    SMS;

    @JsonValue
    public String toLower() {
        return name().toLowerCase();
    }
}
