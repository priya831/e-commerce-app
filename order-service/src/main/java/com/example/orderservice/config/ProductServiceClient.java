package com.example.orderservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product-service", fallback = FeignFallback.class)
public interface ProductServiceClient {
    @GetMapping("/product")
    ResponseEntity getAllProducts();
}
