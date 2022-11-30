package com.ecommerce.customerservice.repository;

import com.ecommerce.customerservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value="select * from address where customer_Id = :customerId", nativeQuery = true)
    List<Address> findByCustomerId(Long customerId);

    @Query(value="select * from address where customer_Id = :customerId and address_type = 'home' ", nativeQuery = true)
    Address findHomeAddressByCustomerId(Long customerId);
}
