package com.store.repository;

import com.store.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findProductCategoriesNameIn(List<String> categoryNames);
}
