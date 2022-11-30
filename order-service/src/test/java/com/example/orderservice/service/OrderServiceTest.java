package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceTest {

    @MockBean
    OrderRepository orderRepository;
    @Autowired
    MockMvc mockMvc;
    @Test
    void testGetAllPendingOrders() throws Exception {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    void testGetRecentOrders() throws Exception {
        Sort sort = Sort.by(Sort.Direction.DESC, "created_date");
        PageRequest page = PageRequest.of(0, 10, sort);
        when(orderRepository.findRecentOrders(page)).thenReturn(new ArrayList<>());
    }

    @Test
    void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus("NEW");
        Date date = new SimpleDateFormat("dd-mm-yyyy").parse("22-03-2021");
        order.setCreatedDate(date);
        when(orderRepository.save(order)).thenReturn(order);
    }

    @Test
    void testGetOrderById() throws Exception{
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus("NEW");
        Date date = new SimpleDateFormat("dd-mm-yyyy").parse("22-03-2021");
        order.setCreatedDate(date);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
    }

    @Test
    void testDeleteOrder() throws Exception{
        doNothing().when(orderRepository).deleteById(1L);
        Assertions.assertThat(orderRepository.findById(1L).isEmpty());
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus("PENDING");
        Date date = new SimpleDateFormat("dd-mm-yyyy").parse("22-03-2021");
        order.setCreatedDate(date);
        when(orderRepository.save(order)).thenReturn(order);
    }

}
