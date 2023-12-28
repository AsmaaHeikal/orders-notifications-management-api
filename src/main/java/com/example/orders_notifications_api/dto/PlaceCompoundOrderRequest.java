package com.example.orders_notifications_api.dto;

import com.example.orders_notifications_api.models.Customer;
import com.example.orders_notifications_api.models.Order;

import java.util.List;

public class PlaceCompoundOrderRequest {
    private String orderId;
    private Customer customer;
    private String shippingAddress;
    private List<Order> orderComponents;
    public PlaceCompoundOrderRequest() {
        // Default constructor
    }

    public PlaceCompoundOrderRequest(String orderId, Customer customer, String shippingAddress, List<Order> orderComponents) {
        this.orderId = orderId;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.orderComponents = orderComponents;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Order> getOrderComponents() {
        return orderComponents;
    }

    public void setOrderComponents(List<Order> orderComponents) {
        this.orderComponents = orderComponents;
    }

}
