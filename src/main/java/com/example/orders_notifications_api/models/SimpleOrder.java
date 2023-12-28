package com.example.orders_notifications_api.models;

public class SimpleOrder extends Order{
    public SimpleOrder(String orderId, Customer customer, String shippingAddress) {
        super(orderId, customer, shippingAddress);
    }
}
