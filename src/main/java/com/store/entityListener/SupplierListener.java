package com.store.entityListener;

import com.store.entity.Order;
import com.store.entity.Supplier;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

public class SupplierListener {

    @PrePersist
    public void beforePersist(Supplier supplier) {
        System.out.println("@PrePersist: New supplier `"+ supplier.getName() + "` is being saved");
    }

    @PostPersist
    public void afterPersist(Supplier supplier) {
        System.out.println("@PostPersist: Supplier created id="+ supplier.getId());
    }
}
