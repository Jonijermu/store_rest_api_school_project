package com.store.entityListener;

import com.store.entity.Customer;
import com.store.entity.Order;
import jakarta.persistence.*;

public class OrderListener {

    @PrePersist
    public void beforePersist(Order order) {
        System.out.println("@PrePersist: New Order `"+ order.getId() + "` is being saved");
    }

    @PostPersist
    public void afterPersist(Order order) {
        System.out.println("@PostPersist: Order created id="+ order.getId());
    }
}
