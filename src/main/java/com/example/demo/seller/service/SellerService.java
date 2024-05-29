package com.example.demo.seller.service;

import com.example.demo.seller.domain.Seller;
import com.example.demo.seller.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    //다인 수정

    public Seller getSeller(String seller) {
        return sellerRepository.findBySellerId(seller);
    }
}
