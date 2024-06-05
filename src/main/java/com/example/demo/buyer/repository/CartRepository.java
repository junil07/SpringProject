package com.example.demo.buyer.repository;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.buyer.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findByBuyer(Buyer buyer);
}
