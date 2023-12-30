package com.example.orders_notifications_api.templates;

public class OrderPlacementTemplate extends NotificationTemplate {

    @Override
    protected String constructMessage(Object... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("OrderPlacementTemplate requires at least 2 arguments.");
        }
        String customerName = String.valueOf(args[0]);
        String orderDetails = String.valueOf(args[1]);
        return "Hello " + customerName + ", thank you for placing an order. Your order details: " + orderDetails;
    }
}
