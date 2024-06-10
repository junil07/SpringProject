package com.example.demo.seller.service;

import com.example.demo.admin.repository.BuyerRepository1;
import com.example.demo.seller.domain.Orderlist;
import com.example.demo.seller.repository.Order_listRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Order_listService {

    private final Order_listRepository order_listRepository;

    @Autowired
    private BuyerRepository1 buyerRepository1;

    @Autowired
    private Order_listRepository orderListRepository;

    public Order_listService(Order_listRepository order_listRepository){
        this.order_listRepository = order_listRepository;
    }

    public List<Orderlist> getAllOrders() {
        return order_listRepository.findAllWithBuyer();
    }

    public void insertOrderList(String buyerId, int payPrice, String buyerAddress){
        Orderlist orderlist = new Orderlist();
        orderlist.setBuyer(buyerRepository1.findByBuyerId(buyerId));
        orderlist.setOrderlistTprice(payPrice);
        orderlist.setOrderlistAddress(buyerAddress);

        orderListRepository.save(orderlist);
    }
}