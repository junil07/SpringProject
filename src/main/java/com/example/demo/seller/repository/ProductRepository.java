package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductId(String productId);
}
