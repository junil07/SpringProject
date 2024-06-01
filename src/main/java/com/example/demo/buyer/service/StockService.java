package com.example.demo.buyer.service;

import com.example.demo.buyer.entity.Stock;
import com.example.demo.buyer.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getStockList(Integer productId){
        return stockRepository.findByProductProductIdOrderByStockSizeAsc(productId);
    }
}
