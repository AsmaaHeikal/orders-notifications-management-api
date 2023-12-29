package com.example.orders_notifications_api.models;

import java.util.Map;

public class CustomerOrderDetails {
    private Customer customer;
    private Map<String, Integer> productsAndQuantities;

    public CustomerOrderDetails(Customer customer, Map<String, Integer> productsAndQuantities) {
        this.customer = customer;
        this.productsAndQuantities = productsAndQuantities;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<String, Integer> getProductsAndQuantities() {
        return productsAndQuantities;
    }
}
