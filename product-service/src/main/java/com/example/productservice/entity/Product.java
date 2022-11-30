package com.example.productservice.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "product")
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;

}
