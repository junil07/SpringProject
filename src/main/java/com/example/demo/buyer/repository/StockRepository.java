package com.example.demo.buyer.repository;

import com.example.demo.seller.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.buyer.entity.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByProductProductIdOrderByStockSizeAsc(long productId);
    void deleteByProduct(Product product);

    Stock findByProductAndStockSize(Product product, Integer stockSize);
}
