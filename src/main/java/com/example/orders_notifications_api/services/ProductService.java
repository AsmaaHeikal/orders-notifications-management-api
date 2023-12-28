package com.example.orders_notifications_api.services;

import java.util.ArrayList;
import com.example.orders_notifications_api.models.Product;
import com.example.orders_notifications_api.repository.ProductsDB;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    ProductsDB productsDB = new ProductsDB();
    //A list of all the products currently available for purchase should be displayed to the user.
   public ArrayList<Product> getAllProducts(){
       return productsDB.getAllProducts();
   }
   //The user should be able to search for a product by its name.
    public Product getProductByName(String name){
         return productsDB.getProductByName(name);
    }
}
