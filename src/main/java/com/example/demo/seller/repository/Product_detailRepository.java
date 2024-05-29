package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Product_detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_detailRepository extends JpaRepository<Product_detail, Long> {
    List<Product_detail> findByProduct_ProductId(String product);

}

