package com.store.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCustomer extends Customer {


    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "billing_email", nullable = false)
    private String billingEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private CustomerAddress customeraddresses;
}
