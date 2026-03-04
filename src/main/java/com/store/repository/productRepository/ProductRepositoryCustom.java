package com.store.repository.productRepository;

import com.store.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> findTopProducts(Integer productNumber);
}
