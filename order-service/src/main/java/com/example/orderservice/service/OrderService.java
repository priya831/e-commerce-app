package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> getAllPendingOrders();
    List<Order> getRecentOrders();
    Order createOrder(Order order);
    Order getOrderById(Long orderId);
    void deleteOrder(Long orderId);
    Order updateOrderStatus(Order order);
}
