package com.example.demo.seller.repository;

import com.example.demo.admin.Entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
