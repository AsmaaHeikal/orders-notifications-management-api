package com.example.orders_notifications_api.models;

import java.util.ArrayList;
import java.util.List;

enum OrderStatus {
    PLACED("Placed"),
    SHIPPED("Shipped"),
    CANCELED("Canceled");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}

public abstract class Order {
    private double orderTotal;
    private double shippingFees;
    private OrderStatus status;

    abstract double calculateOrderTotal();

    abstract double calculateShippingFees();

    abstract void ship();

    abstract OrderStatus getStatus();
}
class SimpleOrder extends Order {

    private String productId;
    private double price;

    public SimpleOrder(String productId, double price) {
        this.productId = productId;
        this.price = price;
    }

    @Override
    public double calculateOrderTotal() {
        return price;
    }

    @Override
    public double calculateShippingFees() {
        // Simple orders might not have shipping fees
        return 0;
    }

    @Override
    public void ship() {
        // Simple orders do not need shipping logic
        System.out.println("SimpleOrder with Product ID " + productId + " has been shipped.");
    }

    @Override
    public OrderStatus getStatus() {
        // Simple orders might not have a status, return a default value
        return OrderStatus.PLACED;
    }
}
class CompoundOrder extends Order {

    private List<Order> orderComponents = new ArrayList<>();

    @Override
    public double calculateOrderTotal() {
        double total = 0;
        for (Order orderComponent : orderComponents) {
            total += orderComponent.calculateOrderTotal();
        }
        return total;
    }

    @Override
    public double calculateShippingFees() {
        double fees = 0;
        for (Order orderComponent : orderComponents) {
            fees += orderComponent.calculateShippingFees();
        }
        return fees;
    }

    @Override
    public void ship() {
        for (Order orderComponent : orderComponents) {
            orderComponent.ship();
        }
        System.out.println("CompoundOrder has been shipped.");
    }

    @Override
    public OrderStatus getStatus() {
        // Compound orders might not have a single status, return a default value
        return OrderStatus.PLACED;
    }

    public void addOrderComponent(Order orderComponent) {
        orderComponents.add(orderComponent);
    }

    public void removeOrderComponent(Order orderComponent) {
        orderComponents.remove(orderComponent);
    }
}