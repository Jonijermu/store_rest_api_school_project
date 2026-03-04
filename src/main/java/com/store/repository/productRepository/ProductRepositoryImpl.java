package com.store.repository.productRepository;

import com.store.entity.OrderItem;
import com.store.entity.Product;
import com.store.utils.enums.OrderStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findTopProducts(Integer productNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<OrderItem> orderItem = cq.from(OrderItem.class);
        Join<OrderItem, Product> productJoin = orderItem.join("product");
        Join<OrderItem, Order> orderJoin = orderItem.join("order");
        Predicate notCancelled = cb.notEqual(orderJoin.get("status"), OrderStatus.CANCELLED);
        cq.select(productJoin)
                .groupBy(productJoin.get("id"))
                .where(notCancelled)
                .orderBy(cb.desc(cb.sum(orderItem.get("quantity")))
        );

        return entityManager.createQuery(cq)
                .setMaxResults(productNumber)
                .getResultList();
    }
}
