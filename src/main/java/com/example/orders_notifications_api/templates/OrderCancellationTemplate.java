package com.example.orders_notifications_api.templates;

public class OrderCancellationTemplate extends NotificationTemplate{
    @Override
    protected String constructMessage(Object... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("OrderPlacementTemplate requires at least 2 arguments.");
        }
        String customerName = String.valueOf(args[0]);
        String orderDetails = String.valueOf(args[1]);
        return "Hello " + customerName + ", you have cancelled your order. Cancelled order details: " + orderDetails;
    }
}
