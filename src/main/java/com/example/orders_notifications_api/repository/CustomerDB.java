package com.example.orders_notifications_api.repository;

import com.example.orders_notifications_api.models.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerDB {
    public ArrayList<Customer>customers;
    public CustomerDB() {
        //add some customers to the list
        customers = new ArrayList<Customer>();
        customers.add(new Customer("1", "Ahmed", 1000, "Ahmed@gmail.com","123456"));
        customers.add(new Customer("2", "Mohamed", 2000, "Mohamed99@gmail.com","qwert"));
    }
    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }
    public Customer getCustomerById(String id){
        for(Customer customer : customers){
            if(customer.getId().equals(id)){
                return customer;
            }
        }
        return null;
    }
    public Customer getCustomerByName(String name){
        for(Customer customer : customers){
            if(customer.getName().equals(name)){
                return customer;
            }
        }
        return null;
    }
    public boolean addCustomer(Customer customer){
        if(getCustomerById(customer.getId()) != null){
            return false;
        }
        customers.add(customer);
        return true;
    }
    public Customer getCustomerByEmail(String email){
        for(Customer customer : customers){
            if(customer.getEmail().equals(email)){
                return customer;
            }
        }
        return null;
    }
}
