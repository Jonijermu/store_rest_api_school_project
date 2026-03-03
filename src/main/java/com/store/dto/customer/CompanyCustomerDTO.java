package com.store.dto.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompanyCustomerDTO extends CustomerDTO {

    private String company;
    private String billingEmail;

}

