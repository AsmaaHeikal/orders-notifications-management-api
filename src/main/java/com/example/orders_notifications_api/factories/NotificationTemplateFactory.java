package com.example.orders_notifications_api.factories;

import com.example.orders_notifications_api.templates.*;
import com.example.orders_notifications_api.types.NotificationType;

public class NotificationTemplateFactory {

    public static NotificationTemplate getTemplate(NotificationType type) {
        switch (type) {
            case ORDER_PLACEMENT:
                return new OrderPlacementTemplate();
            case ORDER_SHIPMENT:
                return new OrderShipmentTemplate();
            case ORDER_CANCELLATION:
                return new OrderCancellationTemplate();
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}
