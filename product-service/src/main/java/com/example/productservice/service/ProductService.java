package com.example.productservice.service;

import com.example.productservice.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product getProductById(Long productId);
    Product getProductByName(String productName);
    void deleteProduct(Long productId);
    Product updateProduct(Product product);
}
