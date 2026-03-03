package com.store.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class CustomerDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
