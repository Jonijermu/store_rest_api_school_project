package com.store.entityListener;

import com.store.entity.Customer;
import jakarta.persistence.*;

public class CustomerListener  {

    @PrePersist
    public void beforePersist(Customer customer) {
        System.out.println("@PrePersist: New Customer `"+ customer.getFirstName() + "` is being saved");
    }

    @PostPersist
    public void afterPersist(Customer customer) {
        System.out.println("@PostPersist: Customer saved, id="+ customer.getId());
    }

    @PreUpdate
    public void beforeUpdate(Customer customer) {
        System.out.println("@PrePersist: Customer `"+ customer.getId() + "` is being updated");
    }

    @PostUpdate
    public void afterUpdate(Customer customer) {
        System.out.println("@PrePersist: Customer `"+ customer.getFirstName() + "` is updated");
    }

    @PreRemove
    public void beforeRemove(Customer customer) {
        System.out.println("@PrePersist: Customer `"+ customer.getId() + "` is being deleted");
    }

    @PostRemove
    public void afterRemove(Customer customer) {
        System.out.println("@PrePersist: Customer `"+ customer.getFirstName() + "` is deleted");
    }


}
