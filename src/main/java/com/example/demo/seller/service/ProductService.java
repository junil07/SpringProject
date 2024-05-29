package com.example.demo.seller.service;

import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.Product_detail;
import com.example.demo.seller.repository.ProductRepository;
import com.example.demo.seller.repository.Product_detailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements SellerService_ {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Product_detailRepository product_detailRepository;

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public List<Product_detail> getProductDetail(String productId) {
        return product_detailRepository.findByProduct_ProductId(productId);
    }







}