package com.example.orders_notifications_api.controllers;

import com.example.orders_notifications_api.models.Customer;
import com.example.orders_notifications_api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/all")
    public ArrayList<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
