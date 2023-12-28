package com.example.orders_notifications_api.controllers;

import com.example.orders_notifications_api.models.*;
import com.example.orders_notifications_api.services.OrderService;
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
        // Assuming PlaceSimpleOrderRequest is a DTO class for request data
        return orderService.placeSimpleOrder(request.getOrderId(), request.getCustomer(), request.getShippingAddress(), request.getProducts());
    }

    @PostMapping("/compound")
    public CompoundOrder placeCompoundOrder(@RequestBody PlaceCompoundOrderRequest request) {
        // Assuming PlaceCompoundOrderRequest is a DTO class for request data
        return orderService.placeCompoundOrder(request.getOrderId(), request.getCustomer(), request.getShippingAddress(), request.getOrderComponents());
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/ship/{orderId}")
    public void shipOrder(@PathVariable String orderId) {
        orderService.shipOrder(orderService.getOrderById(orderId));
    }

    @PostMapping("/addSimple")
    public Order addOrder(@RequestBody SimpleOrder order) {

        return orderService.addOrder(order);
    }

    @PostMapping("/addCompound")
    public Order addOrder(@RequestBody CompoundOrder order) {
        return orderService.addOrder(order);
    }
}
