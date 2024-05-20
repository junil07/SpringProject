package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
