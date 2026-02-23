package com.store.service;

import com.store.entity.Customer;
import com.store.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findCustomerById(int customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomerById(int customerId) {
        customerRepository.deleteById(customerId);
    }

    public void changeCustomerInfo(Customer customer) {
        customerRepository.save(customer);
    }
}
