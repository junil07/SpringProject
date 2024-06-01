package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.seller.domain.Product_detail;

@Repository
public interface Product_detailRepository extends JpaRepository<Product_detail, Long> {
    Product_detail findByProduct(Product product);
}
