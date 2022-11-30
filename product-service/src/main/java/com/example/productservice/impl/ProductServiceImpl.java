package com.example.productservice.impl;

import com.example.productservice.entity.Product;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElseThrow( () -> new ProductNotFoundException("no product found"));
    }

    public Product getProductByName(String productName){
        return productRepository.findByProductName(productName);
    }
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
    public Product updateProduct(Product product){
        Product productToBeUpdated = productRepository.findById(product.getProductId()).get();
        productToBeUpdated.setProductName(product.getProductName());
        return productRepository.save(productToBeUpdated);
    }
}
