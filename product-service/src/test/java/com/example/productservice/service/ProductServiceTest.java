package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {
    @MockBean
    ProductRepository productRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetAllProducts() throws Exception {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    void testAddProduct() throws Exception{
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);
    }

    @Test
    void testGetProductById() throws Exception{
        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product()));
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productRepository).deleteById(1L);
    }

    @Test
    void testUpdateProduct() throws Exception{
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);
    }

    @Test
    void testGetProductByName() throws Exception{
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("UK");
        when(productRepository.findByProductName("UK")).thenReturn(product);
    }

}
