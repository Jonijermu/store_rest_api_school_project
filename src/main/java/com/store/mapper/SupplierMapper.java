package com.store.mapper;

import com.store.dto.supplier.SupplierAddressesDTO;
import com.store.dto.supplier.SupplierDTO;
import com.store.entity.Supplier;
import com.store.entity.SupplierAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierMapper {

    public SupplierDTO toSupplierDto(
            Supplier supplier,
            List<SupplierAddress> addresses
    ) {
        return SupplierDTO.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .contactName(supplier.getContactName())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .addresses(addresses.stream().map(this::toSupplierAddressesDto)
                        .collect(Collectors.toList())
                )
                .build();
    }

    private SupplierAddressesDTO toSupplierAddressesDto(
            SupplierAddress address
    ) {
        return SupplierAddressesDTO.builder()
                .id(address.getId())
                .streetAddress(address.getStreetAddress())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .country(address.getCountry())
                .build();

    }
}
