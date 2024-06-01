package com.example.demo.seller.service;

import com.example.demo.seller.DTO.ProductDTO;

import java.util.List;

public interface SellerService_ {

    public List getProductList();

    public void addProduct(ProductDTO productDTO);


}
