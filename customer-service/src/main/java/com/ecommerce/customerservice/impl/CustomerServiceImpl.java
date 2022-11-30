package com.ecommerce.customerservice.impl;

import com.ecommerce.customerservice.entity.Address;
import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.exception.CustomerNotFoundException;
import com.ecommerce.customerservice.repository.AddressRepository;
import com.ecommerce.customerservice.repository.CustomerRepository;
import com.ecommerce.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;
    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll().stream().collect(Collectors.toList());
    }
    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public Customer getCustomerById(Long customerId){
       return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));
    }
    @Override
    public Customer updateCustomerData(Customer customer){
        Customer customerToBeUpdated = getCustomerById(customer.getCustomerId());
        customerToBeUpdated.setCustomerName(customer.getCustomerName());
        return customerRepository.save(customerToBeUpdated);
    }
    @Transactional
    @Override
    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }
    @Override
    public Customer addAddressToCustomer(Long customerId, Address address){
        List<Address> addresses = new ArrayList<>();
        Customer addressToCustomer = getCustomerById(customerId);
        if(addressToCustomer.getAddress()!=null) {
            addresses = addressToCustomer.getAddress().stream().collect(Collectors.toList());
        }
        addresses.add(address);
        addressToCustomer.setAddress(addresses);
        return customerRepository.save(addressToCustomer);
    }
    @Override
    public Customer updateAddressOfCustomer(Long customerId, Address address){
        Customer customerData = getCustomerById(customerId);
        List<Address> addressToBeUpdated = customerData.getAddress();
        addressToBeUpdated.stream().filter(a->a.getAddressId().equals(address.getAddressId())).forEach(add -> {
                add.setAddressId(address.getAddressId());
                add.setAddressLine1(address.getAddressLine1());
                add.setAddressLine2(address.getAddressLine2());
                add.setCity(address.getCity());
                add.setState(address.getState());
                add.setCountry(address.getCountry());
        });
        customerData.setAddress(addressToBeUpdated);
        return customerRepository.save(customerData);
    }
    @Override
    public List<Address> getAllAddresses(Long customerId){
        return addressRepository.findByCustomerId(customerId);
    }

    @Override
    public Address getHomeAddress(Long customerId){
        return addressRepository.findHomeAddressByCustomerId(customerId);
    }

    @Override
    public void deleteAddress(Long addressId){
        addressRepository.deleteById(addressId);
    }
}
