package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(Long productId);
    Product findByProductCode(String productCode);


    List<Product> findByCategory_CategoryName(String categoryName);

    @Query("SELECT MAX(e.productId) FROM Product e")
    Long findMaxPrimaryKey();

    // "-'sellerId'"를 포함하는 행을 모두 찾는 쿼리 메서드
    @Query("SELECT p FROM Product p WHERE p.seller.sellerId LIKE %:name%")
    List<Product> findBySellerNameContains(@Param("name") String name);

}

