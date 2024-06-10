package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRelationRepository extends JpaRepository<ProductRelation, Long> {
    List<ProductRelation> findByProductRelationOne(int relationNum);
    List<ProductRelation> findByProductRelationTwo(int productRelationTwo);
    ProductRelation findByProductRelationCode(String productRelationCode);
    ProductRelation findByProduct(Product product);

    //ONE 코드 max 값
    @Query("SELECT MAX(e.productRelationOne) FROM ProductRelation e")
    int findMaxOne();

}

