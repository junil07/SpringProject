package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(Long productId);
    Product findByProductCode(String productCode);

    List<Product> findByCategory_CategoryName(String categoryName);
}

