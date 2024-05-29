package com.example.demo.buyer.repository;

import com.example.demo.buyer.DTO.ProductViewDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductViewRepository extends JpaRepository<ProductViewDto,Long> {
    List<ProductViewDto> findByProductId(String productId);

    List<ProductViewDto> findProductDetailSizeByProductId(String productId);
}
