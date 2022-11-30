package com.example.orderservice.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements ProductServiceClient {
    @Override
    public ResponseEntity getAllProducts(){
        return new ResponseEntity("You are in fallback.!!", HttpStatus.OK);
    }
}
