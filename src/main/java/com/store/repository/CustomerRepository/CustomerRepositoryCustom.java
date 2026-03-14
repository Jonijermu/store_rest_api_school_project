package com.store.repository.CustomerRepository;

import com.store.entity.Customer;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepositoryCustom {

    List<Customer> deleteInactiveCustomers(LocalDateTime twoYearsAgo);

}
