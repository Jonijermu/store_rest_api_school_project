package com.store.repository.productRepository;

import com.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {

    Product findProductByName(String productName);

    @Modifying
    @Query("UPDATE Product p SET p.price = p.price * (1 + :increase / 100)")
    void increaseAllPricesOfProducts(@Param("increase")double percentageIncrease);

}
