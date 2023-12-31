package com.example.orders_notifications_api.controllers;

import com.example.orders_notifications_api.models.*;
import com.example.orders_notifications_api.services.*;
import com.example.orders_notifications_api.types.NotificationChannel;
import com.example.orders_notifications_api.types.NotificationStatus;
import com.example.orders_notifications_api.types.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.orders_notifications_api.dto.*;
import com.example.orders_notifications_api.models.Notification;


import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
//    private final NotificationService notificationService;
//    private final NotificationController notificationController=new NotificationController(notificationService);


    @Autowired
    public OrderController(OrderService orderService, NotificationService notificationService) {
        this.orderService = orderService;
//        this.notificationService = notificationService;
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
    @PostMapping("/cancelOrder/{orderId}")
    public Order CancelOrder(@PathVariable String orderId) {
        orderService.CancelOrder(orderService.getOrderById(orderId));
        return orderService.getOrderById(orderId);
    }
    @PostMapping( "/cancelShipping/{orderId}")
    public Order CancelOrderShipping(@PathVariable String orderId) {
        orderService.CancelOrderShipping(orderService.getOrderById(orderId));
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
