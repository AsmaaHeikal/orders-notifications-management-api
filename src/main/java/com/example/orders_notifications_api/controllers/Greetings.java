package com.example.orders_notifications_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greetings {

        @GetMapping("/habal")
        public String habal() {

            return "habal";
        }
}
