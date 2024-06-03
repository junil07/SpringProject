package com.example.demo.admin.repository;

import com.example.demo.admin.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository1 extends JpaRepository<Seller, String> {
    Optional<Seller> findBysellerId(String sellerId);
}
