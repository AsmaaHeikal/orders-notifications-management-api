package com.example.orders_notifications_api.dto;

import com.example.orders_notifications_api.models.Customer;
import com.example.orders_notifications_api.models.Product;

import java.util.List;

public class PlaceSimpleOrderRequest {

    private String orderId;
    private Customer customer;
    private String shippingAddress;
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

