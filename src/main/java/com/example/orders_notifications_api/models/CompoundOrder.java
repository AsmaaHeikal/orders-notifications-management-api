package com.example.orders_notifications_api.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompoundOrder extends Order {
    private ArrayList<Order> orderComponents;

    public CompoundOrder(String orderId, String customerId, String shippingAddress, ArrayList<Order> orderComponents) {
        super(orderId, customerId, shippingAddress);
        this.orderComponents = orderComponents;
    }

    public List<Order> getOrderComponents() {
        return orderComponents;
    }

    public void setOrderComponents(ArrayList<Order> orderComponents) {
        this.orderComponents = orderComponents;
    }

    public void addOrderComponent(Order orderComponent) {
        orderComponents.add(orderComponent);
    }

    public void removeOrderComponent(Order orderComponent) {
        orderComponents.remove(orderComponent);
    }
}
