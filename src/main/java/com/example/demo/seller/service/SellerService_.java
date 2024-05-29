package com.example.demo.seller.service;

import com.example.demo.seller.DTO.ProductDTO;

import java.util.List;

public interface SellerService_ {

    public List getProductList();

    public List getProductDetail(String productId);

    public void addProduct(ProductDTO productDTO);


}
