package com.example.orders_notifications_api.models.common;

public enum OrderStatus {
    PLACED("Placed"),
    SHIPPED("Shipped"),
    ARRIVED("Arrived"),
    CANCELLED_ORDER("Cancelled order"),
    DELIVERED ("Delivered"),
    CANCELLED_SHIPPING("Cancelled Shipping");



    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}

