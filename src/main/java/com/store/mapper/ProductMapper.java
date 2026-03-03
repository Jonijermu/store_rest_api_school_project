package com.store.mapper;

import com.store.dto.product.ProductCategoriesDTO;
import com.store.dto.product.ProductDTO;
import com.store.entity.Product;
import com.store.entity.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDTO toDto(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categories(product.getCategories().stream()
                        .map(this::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private ProductCategoriesDTO toDto(ProductCategory productCategory) {
        return ProductCategoriesDTO.builder()
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .build();
    }
}
