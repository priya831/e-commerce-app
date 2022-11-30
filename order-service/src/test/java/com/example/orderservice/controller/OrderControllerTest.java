package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @Test
    void testGetAllPendingOrders() throws Exception {
        when(orderService.getAllPendingOrders()).thenReturn(new ArrayList<Order>());
        mockMvc.perform(get("/order"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetRecentOrders() throws Exception {
        when(orderService.getRecentOrders()).thenReturn(new ArrayList<Order>());
        mockMvc.perform(get("/order/recent"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus("NEW");
        Date date = new SimpleDateFormat("dd-mm-yyyy").parse("22-03-2021");
        order.setCreatedDate(date);
        when(orderService.createOrder(order)).thenReturn(order);
        mockMvc.perform(post("/order/create").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(order))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetOrderById() throws Exception{
        when(orderService.getOrderById(1L)).thenReturn(new Order());
        mockMvc.perform(get("/order/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteOrder() throws Exception{
        doNothing().when(orderService).deleteOrder(1L);
        mockMvc.perform(delete("/order/delete/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus("PENDING");
        Date date = new SimpleDateFormat("dd-mm-yyyy").parse("22-03-2021");
        order.setCreatedDate(date);
        when(orderService.updateOrderStatus(order)).thenReturn(order);
        mockMvc.perform(put("/order/update").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(order))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
