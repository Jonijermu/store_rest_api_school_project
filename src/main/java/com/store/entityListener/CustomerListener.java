package com.store.entityListener;

import com.store.entity.Customer;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

public class CustomerListener  {

    @PrePersist
    public void beforePersist(Customer customer) {
        System.out.println("@PrePersist: New Customer `"+ customer.getFirstName() + "` is being saved");
    }

    @PostPersist
    public void afterPersist(Customer customer) {
        System.out.println("@PostPersist: Customer saved, id="+ customer.getId());
    }


}
