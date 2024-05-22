package com.example.demo.seller.service;

import com.example.demo.seller.domain.Order_list;
import com.example.demo.seller.repository.Order_listRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Order_listService {

    private final Order_listRepository order_listRepository;

    public Order_listService(Order_listRepository order_listRepository){
        this.order_listRepository = order_listRepository;
    }

    public List<Order_list> getAllOrders() {
        return order_listRepository.findAllWithBuyer();
    }
}