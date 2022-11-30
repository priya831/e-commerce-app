package com.example.orderservice.impl;

import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.NoOrderFoundException;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<Order> getAllPendingOrders(){
        return orderRepository.findAll().stream().filter(order->order.getOrderStatus().equals("PENDING")).collect(Collectors.toList());
    }

    @Override
    public List<Order> getRecentOrders() {
        Sort sort = Sort.by(Sort.Direction.DESC, "created_date");
        PageRequest page = PageRequest.of(0, 10, sort);
        return orderRepository.findRecentOrders(page);
    }

    @Override
    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NoOrderFoundException("No order found.!"));
    }

    @Transactional
    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order updateOrderStatus(Order order) {
        Order updateOrder = orderRepository.findById(order.getOrderId()).get();
        updateOrder.setOrderStatus(order.getOrderStatus());
        return orderRepository.save(updateOrder);
    }
}
