package com.store.service;

import com.store.dto.product.CreateProductRequest;
import com.store.dto.product.ProductDTO;
import com.store.entity.Product;
import com.store.entity.ProductCategory;
import com.store.entity.Supplier;
import com.store.mapper.ProductMapper;
import com.store.repository.CategoryRepository;
import com.store.repository.SupplierRepository;
import com.store.repository.productRepository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;


    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper,
            SupplierRepository supplierRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }


    public ProductDTO getProductById(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow();

        return productMapper.toProductDto(product);
    }


    public List<ProductDTO> getTopProducts(Integer productNumber) {
        List<Product> products = productRepository.findTopProducts(productNumber);
        List<ProductDTO> productsDto = new ArrayList<>();
        for (Product p : products) {
            ProductDTO pDto = productMapper.toProductDto(p);
            productsDto.add(pDto);
        }
        return productsDto;
    }


    @Transactional
    public void increaseProductPrices(double percentageIncrease) {
        productRepository.increaseAllPricesOfProducts(percentageIncrease);

    }


    public ProductDTO createProduct(CreateProductRequest request) {
        Product product = setProductInfo(request);
        Supplier supplier = supplierRepository.findSupplierByName(request.getSupplierName());
        List<ProductCategory> categories = categoryRepository.findByNameIn(request.getCategoryNames());
        product.setSupplier(supplier);
        product.setCategories(categories);
        return productMapper.toProductDto(productRepository.save(product));
    }

    // todo: to be implemented
    public ProductDTO updateProductInfo(Integer productId, CreateProductRequest request) {
        Product product = productRepository.findById(productId).orElseThrow();

        if (product.isLocked()) {
            throw new IllegalStateException("Product is locked.");
        }

        return null;
    }

    public ProductDTO toggleLock(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setLocked(!product.isLocked());
        return productMapper.toProductDto(productRepository.save(product));


    }


    private Product setProductInfo(CreateProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .build();
    }
}
