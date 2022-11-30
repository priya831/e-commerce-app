package com.ecommerce.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="address")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long addressId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String addressType;

}
