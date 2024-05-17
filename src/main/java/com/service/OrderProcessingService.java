package com.service;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderProcessingService {

    private final Map<Long, Order> orders = new HashMap<>();
    private long orderIdCounter = 1;

    public Order createOrder(long productId, int quantity) {
        // Mock validation that productId exists and quantity is available
        long orderId = orderIdCounter++;
        Order order = new Order(orderId, productId, quantity);
        orders.put(orderId, order);
        return order;
    }

    public Order getOrder(long orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }
        return order;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public void updateOrder(long orderId, int quantity) {
        if (!orders.containsKey(orderId)) {
            throw new RuntimeException("Order not found");
        }
        Order order = orders.get(orderId);
        order.setQuantity(quantity);
    }

    public void deleteOrder(long orderId) {
        if (!orders.containsKey(orderId)) {
            throw new RuntimeException("Order not found");
        }
        orders.remove(orderId);
    }
}
