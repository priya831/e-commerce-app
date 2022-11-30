package com.ecommerce.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name="customer")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customerId;
    private String customerName;
    @OneToMany(targetEntity = Address.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private List<Address> address;
    private String emailId;
    private String phoneNumber;

}
