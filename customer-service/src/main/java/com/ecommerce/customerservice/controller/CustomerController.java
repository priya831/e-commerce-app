package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.entity.Address;
import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public ResponseEntity getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") Long customerId){
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        return  ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomerData(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.updateCustomerData(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @PostMapping("/add-address/{id}")
    public ResponseEntity addAddressToCustomer(@PathVariable("id") Long customerId, @RequestBody Address address){
        return ResponseEntity.ok(customerService.addAddressToCustomer(customerId,address));
    }

    @PutMapping("/update-address/{id}")
    public ResponseEntity updateAddressOfCustomer(@PathVariable("id") Long customerId,@RequestBody Address address){
        return ResponseEntity.ok(customerService.updateAddressOfCustomer(customerId,address));
    }

    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity deleteAddressOfCustomer(@PathVariable("id") Long addressId){
        customerService.deleteAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully");
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity getAllAddresses(@PathVariable("id") Long customerId){
        return ResponseEntity.ok(customerService.getAllAddresses(customerId));
    }

    @GetMapping("/home-address/{id}")
    public ResponseEntity getHomeAddress(@PathVariable("id") Long customerId){
        return ResponseEntity.ok(customerService.getHomeAddress(customerId));
    }
}
