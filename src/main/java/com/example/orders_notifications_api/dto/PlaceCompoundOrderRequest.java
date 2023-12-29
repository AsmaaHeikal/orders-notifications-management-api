package com.example.orders_notifications_api.dto;

import com.example.orders_notifications_api.models.*;

import java.util.List;

public class PlaceCompoundOrderRequest {
    private String orderId;
    private Customer mainCustomer;
    private String shippingAddress;
    private List<CustomerOrderDetails> customerOrderDetailsList;

    // Constructors, getters, and setters

    public PlaceCompoundOrderRequest() {
    }

    public PlaceCompoundOrderRequest(String orderId, Customer mainCustomer, String shippingAddress, List<CustomerOrderDetails> customerOrderDetailsList) {
        this.orderId = orderId;
        this.mainCustomer = mainCustomer;
        this.shippingAddress = shippingAddress;
        this.customerOrderDetailsList = customerOrderDetailsList;
    }

    // Getters and setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getMainCustomer() {
        return mainCustomer;
    }

    public void setMainCustomer(Customer mainCustomer) {
        this.mainCustomer = mainCustomer;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<CustomerOrderDetails> getCustomerOrderDetailsList() {
        return customerOrderDetailsList;
    }

    public void setCustomerOrderDetailsList(List<CustomerOrderDetails> customerOrderDetailsList) {
        this.customerOrderDetailsList = customerOrderDetailsList;
    }
}