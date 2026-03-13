package com.store.entityListener;

import com.store.entity.Product;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

public class ProductListener {

    @PrePersist
    public void beforePersist(Product product) {
        System.out.println("@PrePersist: New product `"+ product.getId() + "` is being saved");
    }

    @PostPersist
    public void afterPersist(Product product) {
        System.out.println("@PostPersist: product created id="+ product.getId());
    }
}
