package com.example.orders_notifications_api.models;

public class Product {
    private String serialNumber;
    private String name;
    private String vendor;
    private String category;
    private double price;
    private int remainingParts;
    public Product() {
    }
    public Product(String serialNumber, String name, String vendor, String category, double price, int remainingParts) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.remainingParts = remainingParts;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainingParts() {
        return remainingParts;
    }

    public void setRemainingParts(int remainingParts) {
        this.remainingParts = remainingParts;
    }
    @Override
    public String toString(){
        return serialNumber+"::"+name+"::"+vendor+"::"+category+"::"+price+"::"+remainingParts;
    }
}

