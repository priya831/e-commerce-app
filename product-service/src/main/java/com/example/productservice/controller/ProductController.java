package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable("id") Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("product deleted successfully");
    }
    @GetMapping("/name")
    public ResponseEntity getProductByName(@RequestBody Product product){
        return ResponseEntity.ok(productService.getProductByName(product.getProductName()));
    }

    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(product));
    }
}
