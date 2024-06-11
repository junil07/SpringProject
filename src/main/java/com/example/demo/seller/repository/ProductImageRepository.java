package com.example.demo.seller.repository;

import com.example.demo.admin.Entity.Seller;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage,String> {
    ProductImage findByproduct(Product product);

    List<ProductImage> findByProduct_Category_CategoryNameOrderByProduct_ProductIdDesc(String categoryName);
}
