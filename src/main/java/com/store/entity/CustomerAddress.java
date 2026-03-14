package com.store.entity;


import com.store.entityListener.CustomerAddressListener;
import com.store.entityListener.CustomerListener;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(CustomerAddressListener.class)
@Table(name = "customeraddresses")
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne()
    private Customer customer;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "country", length = 100)
    private String country;

    @OneToMany(mappedBy = "shippingAddress")
    private List<Order> orders = new ArrayList<>();

}