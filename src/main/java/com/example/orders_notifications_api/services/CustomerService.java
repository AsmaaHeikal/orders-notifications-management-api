package com.example.orders_notifications_api.services;
import com.example.orders_notifications_api.repository.CustomerDB;
import com.example.orders_notifications_api.models.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {
    CustomerDB customerDB = new CustomerDB();
    public Customer addCustomer(Customer customer){
        if(customerDB.getCustomerByEmail(customer.getEmail()) == null){
            customerDB.addCustomer(customer);
            return customer;
        }
        return null;
    }
    public ArrayList<Customer> getAllCustomers(){
        return customerDB.getAllCustomers();
    }
}
