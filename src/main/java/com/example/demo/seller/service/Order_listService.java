package com.example.demo.seller.service;

import com.example.demo.seller.domain.Orderlist;
import com.example.demo.seller.repository.Order_listRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Order_listService {

    private final Order_listRepository order_listRepository;

    public Order_listService(Order_listRepository order_listRepository){
        this.order_listRepository = order_listRepository;
    }

    public List<Orderlist> getAllOrders() {
        return order_listRepository.findAllWithBuyer();
    }
}