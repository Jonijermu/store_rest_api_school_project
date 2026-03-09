package com.store.controller;

import com.store.dto.product.CreateProductRequest;
import com.store.dto.product.ProductDTO;
import com.store.entity.Product;
import com.store.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(
            @PathVariable int productId
    ) {
        ProductDTO productDTO = productService.getProductById(productId);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/top/{productNumber}")
    public ResponseEntity<List<ProductDTO>> getTopProducts(
            @PathVariable Integer productNumber
    ) {
        List<ProductDTO> products = productService.getTopProducts(productNumber);
        return ResponseEntity.ok(products);
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> addProduct(
            @RequestBody CreateProductRequest request
    ) {
        ProductDTO productDTO = productService.createProduct(request);
        return ResponseEntity.ok(productDTO);
    }

    @PatchMapping("/{productId}/toggle-lock")
    public ResponseEntity<ProductDTO> toggleLock(
            @PathVariable Integer productId
    ) {
        return ResponseEntity.ok(productService.toggleLock(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Integer productId,
            @RequestBody CreateProductRequest request
    ) {
        ProductDTO productDTO = productService.updateProductInfo(productId, request);
        return ResponseEntity.ok(productDTO);
    }

    @PatchMapping()
    public ResponseEntity<String> patchProductPrices(
            @RequestBody double percentageIncrease
    ) {
        productService.increaseProductPrices(percentageIncrease);
        return ResponseEntity.ok("Product prices increase" + percentageIncrease);
    }
}
