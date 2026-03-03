package com.store.dto.customer;

import com.store.utils.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    private CustomerType type;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String companyName;
    private String billingEmail;
}
