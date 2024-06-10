package com.example.demo.admin.service;

import com.example.demo.buyer.entity.ProductView;
import com.example.demo.seller.domain.Product;

import java.util.List;

public interface ProductService {
    public List getProductList(int activation);
    public List ProductApproval(String productId);
    public List<ProductView> productView(List<Product> product);
    public List ProductStop(String productId);
}
