package com.example.orders_notifications_api.controllers;
import com.example.orders_notifications_api.models.*;
import com.example.orders_notifications_api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.orders_notifications_api.dto.*;



import java.util.List;
@RestController
@RequestMapping("/stats")
public class StatisticsController {
    private final StatisticsService statisticsService;
    @Autowired

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
    @PostMapping("/MostNotifiedEmail")
    public String getMostNotifiedEmail(@RequestBody NotificationService request){
        return statisticsService.MostNotifiedEmail(request);

    }
    @PostMapping("/MostSentTemplate")
    public String getMostSentTemplate(@RequestBody NotificationService request){
        return statisticsService.MostSentNotificationTemplate(request);

    }
}
