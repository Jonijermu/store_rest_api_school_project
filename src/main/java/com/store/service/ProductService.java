package com.store.service;

import com.store.dto.product.ProductDTO;
import com.store.entity.Product;
import com.store.mapper.ProductMapper;
import com.store.repository.productRepository.ProductRepository;
import com.store.repository.productRepository.ProductRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductRepositoryImpl productRepositoryImpl;


    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper, ProductRepositoryImpl productRepositoryImpl) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productRepositoryImpl = productRepositoryImpl;
    }

    public ProductDTO getProductById(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow();

        return productMapper.toDto(product);
    }

    public List<ProductDTO> getTopProducts(Integer productNumber) {
        List<Product> products = productRepository.findTopProducts(productNumber);
        List<ProductDTO> productsDto = new ArrayList<>();
        for (Product p : products) {
            ProductDTO pDto = productMapper.toDto(p);
            productsDto.add(pDto);
        }
        return productsDto;
    }

    @Transactional
    public void increaseProductPrices(double percentageIncrease) {
        productRepository.increaseAllPricesOfProducts(percentageIncrease);

    }
}
