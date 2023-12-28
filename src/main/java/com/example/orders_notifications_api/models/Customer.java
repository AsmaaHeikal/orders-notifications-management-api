package com.example.orders_notifications_api.models;

public class Customer {
    private String id;
    private String name;
    private double balance;
    private String email;
    private String password;
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Customer(String id,String name, double balance, String email, String password) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.email = email;
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Customer() {
    }
    //deduct balance
    public void deductBalance(double amount){
        balance -= amount;
    }
    public String toString(){
        return id+"::"+balance+"::"+email;
    }
}
