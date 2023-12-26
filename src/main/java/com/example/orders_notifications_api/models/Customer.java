package com.example.orders_notifications_api.models;

public class Customer {
    private String id;
    private double balance;
    private String email;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public Customer(String id, double balance, String email) {
        this.id = id;
        this.balance = balance;
        this.email = email;
    }
    public Customer() {
    }
    public String toString(){
        return id+"::"+balance+"::"+email;
    }
}
