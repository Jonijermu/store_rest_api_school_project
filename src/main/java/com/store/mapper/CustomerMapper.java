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
            CompanyCustomer companyCustomer
    ) {
        return CompanyCustomerDTO.builder()
                .id(companyCustomer.getId())
                .firstName(companyCustomer.getFirstName())
                .lastName(companyCustomer.getLastName())
                .email(companyCustomer.getEmail())
                .phone(companyCustomer.getPhone())
                .company(companyCustomer.getCompanyName())
                .billingEmail(companyCustomer.getBillingEmail())
                .build();
    }
}
