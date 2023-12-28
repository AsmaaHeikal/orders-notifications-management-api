package com.example.orders_notifications_api.controllers;

import com.example.orders_notifications_api.models.Product;
import com.example.orders_notifications_api.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService = new ProductService();

    @GetMapping("/all")
    public ArrayList<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search/{name}")
    public Product getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

}
