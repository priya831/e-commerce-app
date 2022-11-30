package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from orders ", nativeQuery = true)
    List<Order> findRecentOrders(Pageable pageRequest);
}
