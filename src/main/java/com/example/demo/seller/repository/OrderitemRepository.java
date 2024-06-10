package com.example.demo.seller.repository;

import java.util.List;
import java.util.Optional;


import com.example.demo.seller.DTO.OrderitemDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.seller.domain.Orderitem;

@Repository
public interface OrderitemRepository extends JpaRepository<Orderitem, Integer> {

    @Query("SELECT o.orderlist.orderlistDate, COUNT(o.orderlist.orderlistId) " +
            "FROM Orderitem o " +
            "JOIN o.orderlist ol " +
            "JOIN o.product p " +
            "JOIN p.seller s " +
            "WHERE s.sellerId = :userId " +
            "GROUP BY o.orderlist.orderlistDate")
    List<Object[]> findCountDateByUserId(@Param("userId") String userId);
    //            "WHERE s.id = 'seller01' \n" +
    @Query("SELECT ol.orderlistDate, COUNT(DISTINCT ol.buyer)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.orderlist ol \n" +
            "JOIN o.product p \n" +
            "JOIN p.seller s \n" +
            "WHERE s.sellerId = :userId " +
            "GROUP BY ol.orderlistDate")
    List<Object[]> findBuyerDate(@Param("userId") String userId);

    @Query("SELECT ol.orderlistDate, SUM(o.orderitemPrice)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.orderlist ol \n" +
            "JOIN o.product p \n" +
            "JOIN p.seller s \n" +
            "WHERE s.sellerId = :userId " +
            "GROUP BY ol.orderlistDate")
    List<Object[]> findTotalPricePerDate(@Param("userId") String userId);

    //제품 판매 비율 원형그래프
    @Query("SELECT o.product.productName, COUNT(o.product.productName) " +
            "FROM Orderitem o " +
            "JOIN o.product p " +
            "JOIN p.seller s \n" +
            "WHERE s.sellerId = :userId " +
            "GROUP BY o.product.id")
    List<Object[]> findsellProduct(@Param("userId") String userId);

    @Query("SELECT o " +
            "FROM Orderitem o " +
            "JOIN o.product p " +
            "JOIN o.orderlist ol " +
            "JOIN ol.buyer b " +
            "WHERE o.orderitemPstatus LIKE '결제전'")
    List<Object[]> findoutstanding();

    @Query("SELECT oi FROM Orderitem oi JOIN oi.orderlist ol WHERE ol.buyer.buyerId = :buyerId ORDER BY oi.orderitemId DESC")
    List<Orderitem> findOrderitemsByBuyerId(@Param("buyerId") String buyerId);
}
