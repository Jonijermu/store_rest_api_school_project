package com.store.entityListener;

import com.store.entity.Customer;
import com.store.entity.CustomerAddress;
import jakarta.persistence.*;

public class CustomerAddressListener {

    @PrePersist
    public void beforePersist(CustomerAddress address) {
        System.out.println("@PrePersist: New address is being saved");
    }

    @PostPersist
    public void afterPersist(CustomerAddress address) {
        System.out.println("@PostPersist: Address saved, id="+ address.getId());
    }

    @PreUpdate
    public void beforeUpdate(CustomerAddress address) {
        System.out.println("@PrePersist: Address `"+ address.getId() + "` is being updated");
    }

    @PostUpdate
    public void afterUpdate(CustomerAddress address) {
        System.out.println("@PrePersist: Address `"+ address.getId() + "` was updated");
    }

    @PreRemove
    public void beforeRemove(CustomerAddress address) {
        System.out.println("@PrePersist: Address `"+ address.getId() + "` is being deleted");
    }

    @PostRemove
    public void afterRemove(CustomerAddress address) {
        System.out.println("@PrePersist: Address `"+ address.getId() + "` was deleted");
    }
}
