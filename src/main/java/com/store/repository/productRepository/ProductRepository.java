package com.store.repository.productRepository;

import com.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {

    Product findProductByName(String productName);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Product p " +
            "SET p.price = p.price * (1 + :increase / 100)," +
            " p.version = p.version + 1")
    void increaseAllPricesOfProducts(@Param("increase")double percentageIncrease);

    List<Product> findByStockQuantityLessThan(int quantity);

}
