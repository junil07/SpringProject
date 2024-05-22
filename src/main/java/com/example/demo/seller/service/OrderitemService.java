package com.example.demo.seller.service;

import com.example.demo.seller.repository.OrderitemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderitemService {

    private final OrderitemRepository orderitemRepository;

    @Autowired
    public OrderitemService(OrderitemRepository orderitemRepository) {
        this.orderitemRepository = orderitemRepository;
    }

    public Map<String, Integer> getTotalPricePerDate() {
        List<Object[]> resultsPrice = orderitemRepository.findTotalPricePerDate();
        Map<String, Integer> price = new HashMap<>();
        for (Object[] result : resultsPrice) {
            String date = result[0].toString();
            Integer totalPrice = ((Number) result[1]).intValue();
            price.put(date, totalPrice);
        }
        return price;
    }

    public Map<String, Integer> findDisBuyer(){
        List<Object[]> resultsBuyer = orderitemRepository.findBuyerDate();
        Map<String, Integer> buyer = new HashMap<>();
        for(Object[] result: resultsBuyer){
            String date = result[0].toString();
            Integer totalBuyer = ((Number) result[1]).intValue();
            buyer.put(date, totalBuyer);
        }
        return buyer;
    }
    public Map<String, Integer> findTotalCount(){
        List<Object[]> resultsCount = orderitemRepository.findCountDate();
        Map<String, Integer> count = new HashMap<>();
        for(Object[] result: resultsCount){
            String date = result[0].toString();
            Integer totalCount = ((Number) result[1]).intValue();
            count.put(date, totalCount);
        }
        return count;
    }

//    public Map<String, Integer> countsellproduct(){
//        List<Object[]> resultssellproduct = orderitemRepository.findsellProduct();
//        Map<String, Integer> buyer = new HashMap<>();
//        for(Object[] result: resultssellproduct){
//            String date = result[0].toString();
//            Integer sellproduct = ((Number) result[1]).intValue();
//            buyer.put(date, sellproduct);
//        }
//        return buyer;
//    }
}
