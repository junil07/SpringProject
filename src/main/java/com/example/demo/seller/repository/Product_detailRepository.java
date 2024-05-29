package com.example.demo.seller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.seller.domain.Product_detail;

@Repository
public interface Product_detailRepository extends JpaRepository<Product_detail, Long> {
    List<Product_detail> findByProduct_ProductId(String productId);

}

