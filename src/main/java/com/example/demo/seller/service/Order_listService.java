package com.example.demo.seller.service;

import com.example.demo.seller.domain.Order_list;
import com.example.demo.seller.repository.Order_listRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Order_listService {

    private final Order_listRepository orderListRepository;

    public Order_listService(Order_listRepository orderListRepository){
        this.orderListRepository = orderListRepository;
    }

    public List<Order_list> getAllOrders() {
        return orderListRepository.findAllWithBuyer();
    }

    public Map<String, Integer> getTotalPricePerDate() {
        List<Object[]> results = orderListRepository.findTotalPricePerDate();
        Map<String, Integer> data = new HashMap<>();
        for (Object[] result : results) {
            String date = result[0].toString();
            Integer totalPrice = ((Number) result[1]).intValue();
            data.put(date, totalPrice);
        }
        return data;
    }
}
