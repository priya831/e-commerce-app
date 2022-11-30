package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.*;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @MockBean
    ProductService productService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddProduct() throws Exception{
        Product product = new Product();
        when(productService.addProduct(product)).thenReturn(product);
        mockMvc.perform(post("/product/add").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(product))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetProductById() throws Exception{
        when(productService.getProductById(1L)).thenReturn(new Product());
        mockMvc.perform(get("/product/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);
        mockMvc.perform(delete("/product/delete/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateProduct() throws Exception{
        Product product = new Product();
        when(productService.updateProduct(product)).thenReturn(product);
        mockMvc.perform(put("/product/update").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(product))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
