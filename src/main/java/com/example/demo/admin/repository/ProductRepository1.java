package com.example.demo.admin.repository;

import com.example.demo.seller.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository1 extends JpaRepository<Product, String> {
    List<Product> findByproductActivation(int activation);
    Optional<Product> findByproductId(long productId);
}
