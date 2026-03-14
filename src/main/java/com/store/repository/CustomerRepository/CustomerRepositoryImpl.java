package com.store.repository.CustomerRepository;

import com.store.entity.Customer;
import com.store.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> deleteInactiveCustomers(LocalDateTime twoYearsAgo) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> customer = cq.from(Customer.class);
        Join<Customer, Order> orders = customer.join("orders", JoinType.LEFT);

        cq.select(customer)
                .where(cb.or(
                        cb.isNull(orders.get("orderDate")),
                        cb.lessThan(orders.get("orderDate"), twoYearsAgo)

                ))
                .distinct(true);

        return entityManager.createQuery(cq).getResultList();

    }
}
