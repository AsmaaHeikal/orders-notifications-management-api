package com.example.orders_notifications_api.repository;

import com.example.orders_notifications_api.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductsDB {
    public ArrayList<Product> products;
    public ProductsDB(){
        //add some products to the list
        products = new ArrayList<Product>();
        products.add(new Product("1","Juhayna Milk","Juhayna","Dairy",80,10));
        products.add(new Product("2","Mobile Charger","DELL","Electronics",100,20));
        products.add(new Product("3","Meat","3am Ahmed","Meat",300,30));
        products.add(new Product("4","Toast","Rich Bake","Bakery",50,40));
        products.add(new Product("5","Chicken","3am Ahmed","Meat",500,50));
    }
    public ArrayList<Product> getAllProducts() {
        return products;
    }
    public Product getProductBySerialNumber(String serialNumber){
        for(Product product : products){
            if(product.getSerialNumber().equals(serialNumber)){
                return product;
            }
        }
        return null;
    }
    public Product getProductByName(String name){
        for(Product product : products){
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }
    public boolean addProduct(Product product){
        if(getProductBySerialNumber(product.getSerialNumber()) != null){
            return false;
        }
        products.add(product);
        return true;
    }
    public boolean deleteProduct(String serialNumber){
        Product product = getProductBySerialNumber(serialNumber);
        if(product == null){
            return false;
        }
        products.remove(product);
        return true;
    }
    public boolean updateProduct(Product product){
        Product product1 = getProductBySerialNumber(product.getSerialNumber());
        if(product1 == null){
            return false;
        }
        product1.setName(product.getName());
        product1.setVendor(product.getVendor());
        product1.setCategory(product.getCategory());
        product1.setPrice(product.getPrice());
        product1.setRemainingParts(product.getRemainingParts());
        return true;
    }

    public boolean deductRemainingParts(String name, int quantity){
        Product product = getProductByName(name);
        if(product == null){
            return false;
        }
        if(product.getRemainingParts() < quantity){
            return false;
        }
        product.setRemainingParts(product.getRemainingParts() - quantity);
        return true;
    }

}
