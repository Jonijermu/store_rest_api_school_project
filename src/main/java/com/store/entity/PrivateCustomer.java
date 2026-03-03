package com.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class PrivateCustomer extends Customer {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private CustomerAddress customeraddresses;
}
