package com.example.orders_notifications_api.services;

import com.example.orders_notifications_api.models.*;
import com.example.orders_notifications_api.models.common.OrderStatus;
import com.example.orders_notifications_api.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    OrderDB orderDB;
    ProductsDB productDB;
    CustomerDB customerDB ;

    public OrderService() {
        orderDB = new OrderDB();
        productDB = new ProductsDB();
        customerDB = new CustomerDB();
    }

    // Method to place a simple order
    public SimpleOrder placeSimpleOrder(String orderId, String customerId, String shippingAddress, Map<String, Integer> productsAndQuantity) {
        SimpleOrder simpleOrder = new SimpleOrder(orderId, customerId, shippingAddress);

        // Add products to the order
        for (Map.Entry<String, Integer> entry : productsAndQuantity.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();

            // Fetch the product from the database
            Product product = productDB.getProductBySerialNumber(productId);

            // Check if the product exists and has enough quantity
            if (product != null && product.getRemainingParts() >= quantity) {
                // Deduct remaining parts from the product
                productDB.deductRemainingParts(productId, quantity);

                // Add the product to the order
                simpleOrder.addProduct(productId, quantity);
            } else {
                // If the product doesn't exist or doesn't have enough quantity, throw an exception
                throw new IllegalArgumentException("Product " + productId + " doesn't exist or doesn't have enough quantity");
            }
        }
        // Calculate order total and add the order
        simpleOrder.setOrderTotal(simpleOrder.calculateOrderTotal());
        simpleOrder.setStatus(OrderStatus.PLACED);
        orderDB.addOrder(simpleOrder);
        //deduct the order total from the customer's balance
        if(!customerDB.deductBalance(customerId, simpleOrder.getOrderTotal())){
            throw new IllegalArgumentException("Customer " + customerId + " doesn't exist or doesn't have enough balance");
        }
        return simpleOrder;
    }

    // Method to place a compound order
    public CompoundOrder placeCompoundOrder(String orderId, Customer mainCustomer, String shippingAddress, List<CustomerOrderDetails> customerOrderDetailsList) {
        // Create a list to store order components
        ArrayList<Order> orderComponents = new ArrayList<>();

        // Validate that the main customer exists
        if (customerDB.getCustomerById(mainCustomer.getId()) == null) {
            throw new IllegalArgumentException("Main customer " + mainCustomer.getId() + " doesn't exist");

        }

        // Iterate through each friend's order details
        for (CustomerOrderDetails details : customerOrderDetailsList) {
            // Extract friend's customer and order details
            Customer friendCustomer = details.getCustomer();
            Map<String, Integer> productsAndQuantities = details.getProductsAndQuantities();

            // Validate that the friend's customer exists
            if (customerDB.getCustomerById(friendCustomer.getId()) == null) {
                throw new IllegalArgumentException("Friend customer " + friendCustomer.getId() + " doesn't exist");
            }

            // Create a simple order for each friend
            SimpleOrder friendOrder = new SimpleOrder(UUID.randomUUID().toString(), friendCustomer.getId(), shippingAddress);

            // Add products to the friend's order
            productsAndQuantities.forEach((productId, value) -> {
                int quantity = value;

                // Fetch the product from the database
                Product product = productDB.getProductBySerialNumber(productId);

                // Check if the product exists and has enough quantity
                if (product != null && product.getRemainingParts() >= quantity) {
                    // Deduct remaining parts from the product
                    productDB.deductRemainingParts(productId, quantity);

                    // Add the product to the friend's order
                    friendOrder.addProduct(productId, quantity);
                } else {
                    // If the product doesn't exist or doesn't have enough quantity, throw an exception
                    throw new IllegalArgumentException("Product " + productId + " doesn't exist or doesn't have enough quantity");
                }
            });

            // Calculate order total and add the friend's order to the list of order components
            friendOrder.setOrderTotal(friendOrder.calculateOrderTotal());
            friendOrder.setStatus(OrderStatus.PLACED);
            orderComponents.add(friendOrder);
        }

        // Create the compound order with the list of order components
        CompoundOrder compoundOrder = new CompoundOrder(orderId, mainCustomer.getId(), shippingAddress, orderComponents);

        // Calculate order total and add the compound order
        compoundOrder.setOrderTotal(compoundOrder.calculateOrderTotal());
        compoundOrder.setStatus(OrderStatus.PLACED);
        orderDB.addOrder(compoundOrder);

        // Deduct the order total from each friend's balance
        for (Order orderComponent : compoundOrder.getOrderComponents()) {
            String friendCustomerId = orderComponent.getCustomerId();
            double friendOrderTotal = orderComponent.getOrderTotal();
            if (!customerDB.deductBalance(friendCustomerId, friendOrderTotal)) {
                throw new IllegalArgumentException("Friend customer " + friendCustomerId + " doesn't exist or doesn't have enough balance");
            }
        }

        return compoundOrder;
    }


    // Method to ship an order
    public void shipOrder(Order order) {
        order.setStatus(OrderStatus.SHIPPED);
        if (order instanceof SimpleOrder) {
            customerDeductShippingFees((SimpleOrder) order);
        } else if (order instanceof CompoundOrder) {
            for (Order orderComponent : ((CompoundOrder) order).getOrderComponents()) {
                customerDeductShippingFees(orderComponent);
                orderComponent.setStatus(OrderStatus.SHIPPED);
            }
        }
    }

    // Helper method to deduct shipping fees from the customer's balance
    public void customerDeductShippingFees(Order order) {
        double shippingFees = order.getShippingFees();
        if(!customerDB.deductBalance(order.getCustomerId(),shippingFees)){
            throw new IllegalArgumentException("Customer " + order.getCustomerId() + " doesn't exist or doesn't have enough balance");
        }
    }

    public Order getOrderById(String id) {
        return orderDB.getOrderById(id);
    }

    public Order addOrder(Order order) {
        if(orderDB.getOrderById(order.getId()) == null){
            orderDB.addOrder(order);
            return order;
        }
        return null;

    }

    //get all products
    public ArrayList<Product> getAllProducts() {
        return productDB.getAllProducts();
    }

    public List<Order> getAllOrders() {
        return orderDB.getAllOrders();
    }

    public List<Customer> getAllCustomers() {
        return customerDB.getAllCustomers();
    }
}
