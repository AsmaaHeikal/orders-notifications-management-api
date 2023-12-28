package com.example.orders_notifications_api.models;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order {
    private List<Order> orderComponents = new ArrayList<>();

    public CompoundOrder(String orderId, Customer customer, String shippingAddress) {
        super(orderId, customer, shippingAddress);
    }

    public List<Order> getOrderComponents() {
        return orderComponents;
    }
    public void setOrderComponents(List<Order> orderComponents) {
        this.orderComponents = orderComponents;
    }
    public void addOrderComponent(Order orderComponent) {
        orderComponents.add(orderComponent);
    }

    public void removeOrderComponent(Order orderComponent) {
        orderComponents.remove(orderComponent);
    }
}
