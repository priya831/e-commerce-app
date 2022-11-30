package com.example.orderservice.controller;

import com.example.orderservice.config.ProductServiceClient;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProductServiceClient productServiceClient;

    @GetMapping()
    public ResponseEntity getAllPendingOrders(){
        return ResponseEntity.ok(orderService.getAllPendingOrders());
    }

    @GetMapping("/recent")
    public ResponseEntity getRecentOrders(){
        return ResponseEntity.ok(orderService.getRecentOrders());
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrderById(@PathVariable("id") Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable("id") Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order Deleted Successfully!!");
    }
    @PutMapping("/update")
    public ResponseEntity updateOrderStatus(@RequestBody Order order){
        return ResponseEntity.ok(orderService.updateOrderStatus(order));
    }

    @GetMapping("/customer")
    public Object callCustomerServiceUsingHardCodedUrl(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange("http://localhost:8184/customer",
                HttpMethod.GET, null, new ParameterizedTypeReference<>(){}).getBody();
    }
    @GetMapping("/customer-new")
    public Object callCustomerServiceUsingServiceName(){
        return restTemplate.exchange("http://customer-service/customer", HttpMethod.GET,
                null, new ParameterizedTypeReference<>(){}).getBody();
    }
    @GetMapping("/product-feign")
    public ResponseEntity callProductServiceUsingFeignClient(){
        return productServiceClient.getAllProducts();
    }
}
