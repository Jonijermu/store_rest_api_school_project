package com.store.mapper;

import com.store.dto.customer.CustomerAddressDTO;
import com.store.entity.CustomerAddress;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public CustomerAddressDTO toDto(CustomerAddress customerAddress) {
        return CustomerAddressDTO.builder()
                .streetAddress(customerAddress.getStreetAddress())
                .postalCode(customerAddress.getPostalCode())
                .city(customerAddress.getCity())
                .country(customerAddress.getCountry())
                .build();
    }
}
