package com.example.demo.buyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.buyer.entity.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByProductProductIdOrderByStockSizeAsc(Integer productId);
}
