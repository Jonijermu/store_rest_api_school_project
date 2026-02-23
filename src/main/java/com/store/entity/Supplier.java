package com.store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_name", length = 100)
    private String contactName;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "supplier")
    private List<SupplierAddress> supplierAddresses = new ArrayList<>();


}