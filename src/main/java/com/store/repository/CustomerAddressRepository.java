package com.store.repository;

import com.store.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {

    CustomerAddress findByCustomerId(Integer customerId);
}
