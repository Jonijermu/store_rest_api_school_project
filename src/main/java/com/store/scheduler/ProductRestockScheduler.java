package com.store.scheduler;

import com.store.entity.Product;
import com.store.repository.productRepository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductRestockScheduler {

    private final ProductRepository productRepository;

    @Scheduled(fixedRate = 60000 * 10)  //10 minutes
    @Transactional
    public void restockProducts() {

        List<Product> products = productRepository.findByStockQuantityLessThan(1);

        for (Product p : products) {
            p.setStockQuantity(20);
        }
    }
}
