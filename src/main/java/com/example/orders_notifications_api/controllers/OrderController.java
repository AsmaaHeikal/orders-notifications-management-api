package com.example.orders_notifications_api.controllers;

import com.example.orders_notifications_api.models.*;
import com.example.orders_notifications_api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.orders_notifications_api.dto.*;


import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/simple")
    public SimpleOrder placeSimpleOrder(@RequestBody PlaceSimpleOrderRequest request) {
        return orderService.placeSimpleOrder(request.getOrderId(), request.getCustomerId(), request.getShippingAddress(), request.getProductsAndQuantity());
    }

    @PostMapping("/compound")
    public CompoundOrder placeCompoundOrder(@RequestBody PlaceCompoundOrderRequest request) {
        Customer mainCustomer = request.getMainCustomer();
        List<CustomerOrderDetails> customerOrderDetailsList = request.getCustomerOrderDetailsList();
        return orderService.placeCompoundOrder(request.getOrderId(), mainCustomer, request.getShippingAddress(), customerOrderDetailsList);
    }

    @PostMapping("/ship/{orderId}")
    public Order shipOrder(@PathVariable String orderId) {
        orderService.shipOrder(orderService.getOrderById(orderId));
        return orderService.getOrderById(orderId);
    }

    //print all products
    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {
        return orderService.getAllProducts();
    }

    //print all orders
    @GetMapping("/allOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    //print all customers
    @GetMapping("/allCustomers")
    public List<Customer> getAllCustomers() {
        return orderService.getAllCustomers();
    }
}
