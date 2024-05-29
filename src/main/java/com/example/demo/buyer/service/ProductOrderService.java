package com.example.demo.buyer.service;

import com.example.demo.buyer.DTO.ProductOrderSummaryDTO;
import com.example.demo.seller.repository.OrderitemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductOrderService {

    private final OrderitemRepository orderItemRepository;

    public ProductOrderService(OrderitemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
}
