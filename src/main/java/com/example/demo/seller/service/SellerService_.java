package com.example.demo.seller.service;

import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.Product_detail;

import java.util.List;

public interface SellerService_ {

    public List getProductList();

    public List getProductDetail(String productId);

}
