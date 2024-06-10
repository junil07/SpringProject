package com.example.demo.admin.repository;

import com.example.demo.buyer.entity.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductViewRepository1 extends JpaRepository<ProductView, String> {
    Optional<ProductView> findByproductId(Integer productId);
}
