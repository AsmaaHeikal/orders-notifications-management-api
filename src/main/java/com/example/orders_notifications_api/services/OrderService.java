package com.example.orders_notifications_api.services;

import com.example.orders_notifications_api.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    // Assuming you have a list to store orders
    public List<Order> orderList;

    public OrderService() {
        orderList = new ArrayList<>();
    }

    // Method to place a simple order
    public SimpleOrder placeSimpleOrder(String orderId, Customer customer, String shippingAddress, List<Product> products) {
        SimpleOrder simpleOrder = new SimpleOrder(orderId, customer, shippingAddress);
        simpleOrder.setProducts((ArrayList<Product>) products);
        simpleOrder.setOrderTotal(simpleOrder.calculateOrderTotal());
        customer.deductBalance(simpleOrder.getOrderTotal());
        orderList.add(simpleOrder);
        return simpleOrder;
    }

    // Method to place a compound order
    public CompoundOrder placeCompoundOrder(String orderId, Customer customer, String shippingAddress, List<Order> orderComponents) {
        CompoundOrder compoundOrder = new CompoundOrder(orderId, customer, shippingAddress);
        compoundOrder.setOrderComponents(orderComponents);
        compoundOrder.setOrderTotal(compoundOrder.calculateOrderTotal());
        customer.deductBalance(compoundOrder.getOrderTotal());
        orderList.add(compoundOrder);
        return compoundOrder;
    }

    // Method to list details of all orders
    public List<Order> getAllOrders() {
        return orderList;
    }

    // Method to ship an order
    public void shipOrder(Order order) {
        order.ship();
        if (order instanceof SimpleOrder) {
            customerDeductShippingFees((SimpleOrder) order);
        } else if (order instanceof CompoundOrder) {
            for (Order orderComponent : ((CompoundOrder) order).getOrderComponents()) {
                customerDeductShippingFees(orderComponent);
            }
        }
    }

    // Helper method to deduct shipping fees from the customer's balance
    public void customerDeductShippingFees(Order order) {
        Customer customer = order.getCustomerId();
        double shippingFees = order.getShippingFees();
        customer.deductBalance(shippingFees);
    }

    public Order getOrderById(String id) {
        for (Order order : orderList) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }
    public Order addOrder(Order order){
        if(getOrderById(order.getId()) != null){
            return null;
        }
        orderList.add(order);
        return order;
    }
}
