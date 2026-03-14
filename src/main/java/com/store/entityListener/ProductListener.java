package com.store.entityListener;

import com.store.entity.Product;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class ProductListener {

    @PrePersist
    public void beforePersist(Product product) {
        System.out.println("@PrePersist: New product `"+ product.getId() + "` is being saved");
    }

    @PostPersist
    public void afterPersist(Product product) {
        System.out.println("@PostPersist: product created id="+ product.getId());
    }

    @PreUpdate
    public void beforeUpdate(Product product) {
        System.out.println("@PostPersist: product with id="+ product.getId() +" is being updated");
    }

    @PostUpdate
    public void afterUpdate(Product product) {
        System.out.println("@PostPersist: product with id="+ product.getId() + " was updated");
    }
}
