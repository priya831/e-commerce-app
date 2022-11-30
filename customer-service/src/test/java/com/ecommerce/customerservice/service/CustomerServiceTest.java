package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.entity.Address;
import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.exception.CustomerNotFoundException;
import com.ecommerce.customerservice.repository.AddressRepository;
import com.ecommerce.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private AddressRepository addressRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllCustomer() throws Exception {
        List<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(2L);
        customer.setCustomerName("AB");
        customer.setAddress(new ArrayList<>());
        customer.setEmailId("swam@gmil.com");
        customer.setPhoneNumber("76656454445");
        when(customerRepository.findById(2L)).thenReturn(Optional.of(customer));
    }

    @Test
    void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("AB");
        customer.setAddress(new ArrayList<>());
        customer.setEmailId("swam@gmil.com");
        customer.setPhoneNumber("76656454445");
        when(customerRepository.save(customer)).thenReturn(customer);
    }

    @Test
    void testUpdateCustomerData() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("UKG");
        customer.setAddress(new ArrayList<>());
        customer.setEmailId("swam@gmil.com");
        customer.setPhoneNumber("76656454445");
        when(customerRepository.save(customer)).thenReturn(customer);
    }

    @Test
    void testGetCustomerByIdThrowsException() throws Exception {
        when(customerRepository.findById(2L)).thenThrow(new CustomerNotFoundException());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(customerRepository).deleteById(1L);
    }

    @Test
    void testAddAddressToCustomer() throws Exception{
        Address address = new Address();
        address.setAddressLine1("flat no 123");
        address.setAddressLine2("lpu");
        address.setCity("gzb");
        address.setState("Delhi");
        address.setCountry("india");
        Customer customer = new Customer();
        customer.setAddress(new ArrayList<>(Arrays.asList(address)));
        when(customerRepository.save(customer)).thenReturn(new Customer());
    }

    @Test
    void testDeleteAddressOfCustomer() throws Exception{
        doNothing().when(customerRepository).deleteById(1L);
    }

    @Test
    void testGetAllAddresses() throws Exception {
        List<Address> addressList = new ArrayList<>();
        Address address = new Address();
        address.setAddressLine1("preetvihar");
        address.setAddressLine2("lpu");
        address.setCity("gzb");
        address.setState("Delhi");
        address.setCountry("india");
        addressList.add(address);
        when(addressRepository.findByCustomerId(1L)).thenReturn(addressList);

    }

    @Test
    void testGetHomeAddress() throws Exception {
        Address address = new Address();
        address.setAddressId(1L);
        address.setAddressLine1("preetvihar");
        address.setAddressLine2("lpu");
        address.setCity("gzb");
        address.setState("Delhi");
        address.setCountry("india");
        address.setAddressType("home");
        when(addressRepository.findHomeAddressByCustomerId(1L)).thenReturn(address);
    }
}
