package com.store.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAddressDTO {

    private String streetAddress;
    private String postalCode;
    private String city;
    private String country;
}
