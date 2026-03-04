package com.store.mapper;

import com.store.dto.customer.CompanyCustomerDTO;
import com.store.dto.customer.PrivateCustomerDTO;
import com.store.entity.CompanyCustomer;

import com.store.entity.PrivateCustomer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public PrivateCustomerDTO toPrivateCustomerDto(
            PrivateCustomer customer
    ) {
        return PrivateCustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }

    public CompanyCustomerDTO toCompanyCustomerDto(
            CompanyCustomer customer
    ) {
        return CompanyCustomerDTO.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .company(customer.getCompanyName())
                .billingEmail(customer.getBillingEmail())
                .build();
    }
}
