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
    protected double orderTotal;
    protected final double shippingFees=20;
    protected OrderStatus status;
    protected String id;
    protected Customer customer;
    protected String shippingAddress;
    protected ArrayList<Product> products;

    public double calculateOrderTotal() {
        //the order total is the sum of the prices of all products in the order
        double total = 0;
        for (Product product :products) {
            total += product.getPrice();
        }
        return total;
    }
    public void ship(){
        status = OrderStatus.SHIPPED;
    }
    public Order() {
        products = new ArrayList<Product>();
    }
    public Order(String id, Customer customer, String shippingAddress) {
        this.id = id;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        products = new ArrayList<Product>();
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    OrderStatus getStatus(){
        return status;
    }
    public double getOrderTotal() {
        return orderTotal;
    }
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
    public double getShippingFees() {
        return shippingFees;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Customer getCustomerId() {
        return customer;
    }
    public void setCustomerId(Customer customer) {
        this.customer = customer;
    }
    public String getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
