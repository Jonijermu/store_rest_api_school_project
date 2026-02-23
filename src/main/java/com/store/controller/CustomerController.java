package com.store.controller;

import com.store.entity.Customer;
import com.store.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    @PostMapping()
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.ok("Customer added successfully");
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> addCustomer(@PathVariable int customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
