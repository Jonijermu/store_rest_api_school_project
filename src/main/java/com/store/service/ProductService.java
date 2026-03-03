package com.store.service;

import com.store.dto.product.ProductDTO;
import com.store.entity.Product;
import com.store.mapper.ProductMapper;
import com.store.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDTO getProductById(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow();

        return productMapper.toDto(product);
    }
}
