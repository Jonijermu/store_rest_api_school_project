package com.store.controller;

import com.store.dto.customer.CompanyCustomerDTO;
import com.store.dto.customer.CreateCustomerRequest;
import com.store.dto.customer.CustomerDTO;
import com.store.dto.customer.PrivateCustomerDTO;
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
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int customerId) {
        CustomerDTO CustomerDto = customerService.findCustomerById(customerId);
        return ResponseEntity.ok(CustomerDto);
    }


    @PostMapping()
    public ResponseEntity<CustomerDTO> createCustomer(
            @RequestBody CreateCustomerRequest request
    ) {
        CustomerDTO customerDto = customerService.saveCustomer(request);
        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable int customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomerInfo(
            @PathVariable int customerId,
            @RequestBody CreateCustomerRequest request
    ) {
        CustomerDTO customerDTO = customerService.updateCustomerInfo(customerId, request);
        return ResponseEntity.ok(customerDTO);
    }
}
