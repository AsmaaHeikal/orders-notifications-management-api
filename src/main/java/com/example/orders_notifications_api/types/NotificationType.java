package com.example.orders_notifications_api.types;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationType {
    ORDER_PLACEMENT,
    ORDER_SHIPMENT;

    @JsonValue
    public String toLower() {
        return name().toLowerCase();
    }
}