package com.example.orders_notifications_api.dto;

import com.example.orders_notifications_api.models.Customer;
import com.example.orders_notifications_api.models.Product;

import java.util.Map;

public class PlaceSimpleOrderRequest {
    private String orderId;
    private String customerId;
    private String shippingAddress;
    private Map<String, Integer> productsAndQuantity;

    // Constructors, getters, and setters

    public PlaceSimpleOrderRequest() {
    }

    public PlaceSimpleOrderRequest(String orderId, String customerId, String shippingAddress, Map<String, Integer> productsAndQuantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.shippingAddress = shippingAddress;
        this.productsAndQuantity = productsAndQuantity;
    }

    // Getters and setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Map<String, Integer> getProductsAndQuantity() {
        return productsAndQuantity;
    }

    public void setProductsAndQuantity(Map<String, Integer> productsAndQuantity) {
        this.productsAndQuantity = productsAndQuantity;
    }
}