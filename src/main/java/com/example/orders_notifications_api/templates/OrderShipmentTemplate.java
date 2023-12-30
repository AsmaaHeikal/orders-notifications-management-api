package com.example.orders_notifications_api.templates;

public class OrderShipmentTemplate extends NotificationTemplate {

    @Override
    protected String constructMessage(Object... args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("OrderShipmentTemplate requires at least 3 arguments.");
        }
        String customerName = String.valueOf(args[0]);
        String orderId = String.valueOf(args[1]);
        String trackingInfo = String.valueOf(args[2]);
        return "Dear " + customerName + ", your order (ID: " + orderId + ") has been shipped. Tracking info: "
                + trackingInfo;
    }
}
