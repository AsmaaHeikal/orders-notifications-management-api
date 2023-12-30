package com.example.orders_notifications_api.types;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationStatus {
    SENT,
    PENDING;

    @JsonValue
    public String toLower() {
        return name().toLowerCase();
    }
}
