package com.example.demo.admin.repository;

import com.example.demo.admin.Entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository1 extends JpaRepository<Buyer, String> {
    Optional<Buyer> findBybuyerId(String buyerId);
}
