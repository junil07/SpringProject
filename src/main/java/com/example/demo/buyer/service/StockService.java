package com.example.demo.buyer.service;

import com.example.demo.buyer.entity.Stock;
import com.example.demo.buyer.repository.StockRepository;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductService productService;

    public List<Stock> getStockList(long productId) {
        return stockRepository.findByProductProductIdOrderByStockSizeAsc(productId);
    }

    public List<List<Object>> getNewStockList(List<Object> totalStock) {
        List<List<Object>> newStocks = new ArrayList<>();

        int num = 0;
        for (int i = 0; i < totalStock.size()/2; i++) {
            List<Object> sublist = new ArrayList<>();
                for(int j = 0; j < 2; j++) {
                    sublist.add(totalStock.get(num));
                    num++;
                }
            newStocks.add(sublist);
        }
        return newStocks;
    }

    @Transactional
    public void stockResult(List<List<Object>> newStocks, long productId) {
        List<Stock> resultStocks = new ArrayList<>();

        //전체 삭제
        stockRepository.deleteByProduct(productService.getProduct(productId));

        //newStocks 변환
        for (int i = 0; i < newStocks.size(); i++) {
            Stock stock = new Stock();

            stock.setStockSize(Integer.parseInt((String)newStocks.get(i).get(0)));
            stock.setStockCount(Integer.parseInt((String)newStocks.get(i).get(1)));
            stock.setProduct(productService.getProduct(productId));

            resultStocks.add(stock);
        }

        //전체 저장
        stockRepository.saveAll(resultStocks);
    }

    public void updateStock(List<Long> productIds,List<Integer> productCounts,List<Integer> productSizes){
        for(int i=0;i<productIds.size();i++){
            Product product = productService.getProduct(productIds.get(i));
            int productSize = productSizes.get(i);
            Stock stocks = stockRepository.findByProductAndStockSize(product,productSize);
            stocks.setStockCount(stocks.getStockCount()-productCounts.get(i));
            stockRepository.save(stocks);
        }
    }
}
