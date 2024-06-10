package com.example.demo.admin.repository;

import com.example.demo.admin.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository1 extends JpaRepository<Seller, String> {
    Optional<Seller> findBysellerId(String sellerId);

    //박승섭 임시 사용중
    List<Seller> findBySellerId(@Param("sellerId") String sellerId);
}
