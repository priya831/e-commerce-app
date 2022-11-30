package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name="orders")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderId;
    private String orderStatus;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date createdDate;

    public void setCreatedDate(Date date) {
    }
}
