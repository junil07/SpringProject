package com.example.demo.seller.repository;

import com.example.demo.admin.Entity.Seller;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage,String> {
    ProductImage findByproduct(Product product);
}
