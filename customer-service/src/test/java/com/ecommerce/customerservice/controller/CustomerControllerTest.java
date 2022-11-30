package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.entity.Address;
import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.exception.CustomerNotFoundException;
import com.ecommerce.customerservice.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllCustomer() throws Exception {
        List<Customer> customerList = new ArrayList<>();
        when(customerService.getAllCustomers()).thenReturn(customerList);
        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(2L);
        customer.setCustomerName("AB");
        customer.setAddress(new ArrayList<>());
        customer.setEmailId("swam@gmil.com");
        customer.setPhoneNumber("76656454445");
        when(customerService.getCustomerById(2L)).thenReturn(customer);
        mockMvc.perform(get("/customer/{id}", 2L))
                .andExpect(status().isOk());
    }

    @Test
    void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("AB");
        customer.setAddress(new ArrayList<>());
        customer.setEmailId("swam@gmil.com");
        customer.setPhoneNumber("76656454445");
        when(customerService.addCustomer(customer)).thenReturn(customer);
        mockMvc.perform(post("/customer/add").contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(customer))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCustomerData() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("UKG");
        customer.setAddress(new ArrayList<>());
        customer.setEmailId("swam@gmil.com");
        customer.setPhoneNumber("76656454445");
        when(customerService.updateCustomerData(customer)).thenReturn(customer);
        mockMvc.perform(put("/customer/update").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(customer))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCustomerByIdThrowsException() throws Exception {
        when(customerService.getCustomerById(2L)).thenThrow(new CustomerNotFoundException());
        mockMvc.perform(get("/customer/{id}", 2L))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).deleteCustomer(1L);
        mockMvc.perform(delete("/customer/delete/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testAddAddressToCustomer() throws Exception{
        Address address = new Address();
        address.setAddressLine1("flat no 123");
        address.setAddressLine2("lpu");
        address.setCity("gzb");
        address.setState("Delhi");
        address.setCountry("india");
        when(customerService.addAddressToCustomer(1L, address)).thenReturn(new Customer());
        mockMvc.perform(post("/customer/add-address/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(address))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAddressOfCustomer() throws Exception{
        doNothing().when(customerService).deleteAddress(1L);
        mockMvc.perform(delete("/customer/delete-address/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllAddresses() throws Exception {
        List<Address> addressList = new ArrayList<>();
        when(customerService.getAllAddresses(1L)).thenReturn(addressList);
        mockMvc.perform(get("/customer/addresses/{id}",1L))
                .andExpect(status().isOk());
    }

    @Test
    void testGetHomeAddress() throws Exception {
        when(customerService.getHomeAddress(1L)).thenReturn(new Address());
        mockMvc.perform(get("/customer/home-address/{id}",1L))
                .andExpect(status().isOk());
    }
}
