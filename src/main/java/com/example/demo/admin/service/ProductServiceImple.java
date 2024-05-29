package com.example.demo.admin.service;

import com.example.demo.admin.repository.ProductRepository1;
import com.example.demo.seller.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImple implements ProductService {

    private ProductRepository1 productRepository1;

    public ProductServiceImple(ProductRepository1 productRepository1) {
        this.productRepository1 = productRepository1;
    }

    @Override
    public List getProductList(int activation) {
        List<Product> productList = productRepository1.findByproductActivation(activation);
        return productList;
    }
}
