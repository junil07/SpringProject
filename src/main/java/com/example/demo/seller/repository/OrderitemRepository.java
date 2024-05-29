package com.example.demo.seller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.seller.domain.Orderitem;

@Repository
public interface OrderitemRepository extends JpaRepository<Orderitem, Long> {

    @Query("SELECT o.orderlist.orderlistDate, COUNT(o.orderlist.orderlistId)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.orderlist ol \n" +
            "JOIN o.product p \n" +
            "JOIN p.seller s \n" +
//            "WHERE s.id = 'seller01' \n" +
            "GROUP BY o.orderlist.orderlistDate")
    List<Object[]> findCountDate();

    @Query("SELECT ol.orderlistDate, COUNT(DISTINCT ol.buyer)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.orderlist ol \n" +
            "JOIN o.product p \n" +
            "JOIN p.seller s \n" +
//            "WHERE s.id = 'seller01' \n" +
            "GROUP BY ol.orderlistDate")
    List<Object[]> findBuyerDate();

    @Query("SELECT ol.orderlistDate, SUM(o.orderitemPrice)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.orderlist ol \n" +
            "JOIN o.product p \n" +
            "JOIN p.seller s \n" +
//            "WHERE s.id = 'seller01' \n" +
            "GROUP BY ol.orderlistDate")
    List<Object[]> findTotalPricePerDate();

    //제품 판매 비율 원형그래프
    @Query("SELECT o.product.productName, COUNT(o.product.productName) " +
            "FROM Orderitem o " +
            "JOIN o.product p " +
            "JOIN p.seller s \n" +
//            "WHERE s.id = 'seller01' \n" +
            "GROUP BY o.product.id")
    List<Object[]> findsellProduct();

    @Query("SELECT o " +
            "FROM Orderitem o " +
            "JOIN o.product p " +
            "JOIN o.orderlist ol " +
            "JOIN ol.buyer b " +
            "WHERE o.orderitemPstatus LIKE '결제전'")
    List<Object[]> findoutstanding();
}