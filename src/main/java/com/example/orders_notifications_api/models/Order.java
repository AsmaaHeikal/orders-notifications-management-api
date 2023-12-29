package com.example.orders_notifications_api.models;

import com.example.orders_notifications_api.models.common.OrderStatus;
import com.example.orders_notifications_api.repository.ProductsDB;
import java.util.HashMap;
import java.util.Map;

public abstract class Order {
    protected double orderTotal;
    protected final double shippingFees = 20;
    protected OrderStatus status;
    protected String id;
    protected String customerId;
    protected String shippingAddress;
    protected Map<String, Integer> productsAndQuantity= new HashMap<>();

    public double calculateOrderTotal() {
        // the order total is the sum of the prices of all products in the order
        double total = 0;
        ProductsDB productsDB = new ProductsDB();
        for (Map.Entry<String, Integer> entry : productsAndQuantity.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = productsDB.getProductBySerialNumber(productId);
            if (product != null) {
                total += product.getPrice() * quantity;
            }
        }
        return total;
    }
    public Order() {
        productsAndQuantity = new HashMap<>();
    }

    public Order(String id, String customerId, String shippingAddress) {
        this.id = id;
        this.customerId = customerId;
        this.shippingAddress = shippingAddress;
    }

    public boolean addProduct(String productId, int quantity) {
        ProductsDB productsDB = new ProductsDB();
        if (productsDB.getProductBySerialNumber(productId) != null) {
            if (productsDB.getProductBySerialNumber(productId).getRemainingParts() >= quantity) {
                productsDB.deductRemainingParts(productId, quantity);
                productsAndQuantity.put(productId, quantity);
                return true;
            }
        }
        return false;
    }

    public boolean removeProduct(String productId) {
        if (productsAndQuantity.containsKey(productId)) {
            productsAndQuantity.remove(productId);
            return true;
        }
        return false;
    }

    public Map<String, Integer> getProductsAndQuantity() {
        return productsAndQuantity;
    }

    public void setProductsAndQuantity(Map<String, Integer> productsAndQuantity) {
        this.productsAndQuantity = productsAndQuantity;
    }

    public OrderStatus getStatus() {
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
}

