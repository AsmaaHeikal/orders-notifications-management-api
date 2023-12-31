package com.example.orders_notifications_api.repository;

import com.example.orders_notifications_api.models.Order;
import com.example.orders_notifications_api.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class OrderDB {
    public ArrayList<Order> orders;
    public OrderDB() {
        orders = new ArrayList<Order>();
    }
    public boolean addOrder(Order order){
        if(getOrderById(order.getId()) != null){
            return false;
        }
        orders.add(order);
        return true;
    }
    public ArrayList<Order> getAllOrders() {
        return orders;
    }
    public Order getOrderById(String id) {
        for(Order order : orders){
            if(order.getId().equals(id)){
                return order;
            }
        }
        return null;
    }
    public boolean deleteOrder(String id) {
        Order order = getOrderById(id);
        if(order == null){
            return false;
        }
        orders.remove(order);
        return true;
    }

}
