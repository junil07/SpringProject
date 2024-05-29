package com.example.demo.buyer.repository;

import com.example.demo.buyer.entity.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductViewRepository extends JpaRepository<ProductView,Long> {
    List<ProductView> findByProductId(String productId);

    List<ProductView> findProductDetailSizeByProductId(String productId);
}
