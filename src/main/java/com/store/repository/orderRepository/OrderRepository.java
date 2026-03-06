package com.store.repository.orderRepository;

import com.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> , OrderRepositoryCustom{

    List<Order> findOrderByCustomerId(int customerId);
}
