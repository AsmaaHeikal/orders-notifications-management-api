package com.example.orders_notifications_api.controllers;
import com.example.orders_notifications_api.models.*;
import com.example.orders_notifications_api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Queue;

@RestController
@RequestMapping("/stats")
public class StatisticsController {
    @Autowired
    private final StatisticsService statisticsService;
    @Autowired
    private final OrderService orderService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService, OrderService orderService) {
        this.statisticsService = statisticsService;
        this.orderService = orderService;
    }


    @GetMapping("/MostNotifiedEmail")
    public String getMostNotifiedEmail(){
        return statisticsService.MostNotifiedEmail(orderService.getNotificationService());

    }
    @GetMapping("/MostSentTemplate")
    public String getMostSentTemplate(){
        return statisticsService.MostSentNotificationTemplate(orderService.getNotificationService());

    }
    @GetMapping("/all")
    public ArrayList<Notification> getAll() {
        return statisticsService.getAllNotifications(orderService.getNotificationService());
    }
    @GetMapping("/queue")
    public Queue<Notification> getQueue() {
        return statisticsService.getNotificationQueue(orderService.getNotificationService());
    }
}
