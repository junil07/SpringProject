package com.example.demo.admin.service;

import java.util.List;

public interface ProductService {
    public List getProductList(int activation);
    public List ProductApproval(String productId);
}
