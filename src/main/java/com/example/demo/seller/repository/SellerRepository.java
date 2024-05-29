package com.example.demo.seller.repository;

import com.example.demo.admin.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    //다인 수정
    Seller findBySellerId(String seller);
}
