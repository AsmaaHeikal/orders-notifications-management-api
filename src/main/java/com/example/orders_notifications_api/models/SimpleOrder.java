package com.example.orders_notifications_api.models;

public class SimpleOrder extends Order {
    public SimpleOrder(String orderId, String customerId, String shippingAddress) {
        super(orderId, customerId, shippingAddress);
    }
}
