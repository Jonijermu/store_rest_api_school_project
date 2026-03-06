package com.store.dto.supplier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierAddressesDTO {

    private Integer id;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String country;
}
