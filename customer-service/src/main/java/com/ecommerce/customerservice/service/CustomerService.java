package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.entity.Address;
import com.ecommerce.customerservice.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer addCustomer(Customer customer);

    Customer getCustomerById(Long customerId);
    Customer updateCustomerData(Customer customer);
    void deleteCustomer(Long customerId);
    Customer addAddressToCustomer(Long customerId, Address address);
    Customer updateAddressOfCustomer(Long customerId, Address address);
    List<Address> getAllAddresses(Long customerId);

    Address getHomeAddress(Long customerId);
    void deleteAddress(Long addressId);
}
