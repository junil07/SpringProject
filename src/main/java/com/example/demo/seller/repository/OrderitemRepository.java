package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Orderitem;
import com.example.demo.seller.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderitemRepository extends JpaRepository<Orderitem, Long> {

    @Query("SELECT o.order_list.order_list_Date, COUNT(o.order_list.order_list_Id)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.order_list ol \n" +
            "JOIN o.productId p \n" +
            "JOIN p.seller s \n" +
//            "WHERE s.id = 'seller01' \n" +
            "GROUP BY o.order_list.order_list_Date")
    List<Object[]> findCountDate();

    @Query("SELECT ol.order_list_Date, COUNT(DISTINCT ol.buyer)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.order_list ol \n" +
            "JOIN o.productId p \n" +
            "JOIN p.seller s \n" +
//            "WHERE s.id = 'seller01' \n" +
            "GROUP BY ol.order_list_Date")
    List<Object[]> findBuyerDate();

    @Query("SELECT ol.order_list_Date, SUM(o.orderitemPrice)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.order_list ol \n" +
            "JOIN o.productId p \n" +
            "JOIN p.seller s \n" +
//            "WHERE s.id = 'seller01' \n" +
            "GROUP BY ol.order_list_Date")
    List<Object[]> findTotalPricePerDate();

    //제품 판매 비율 원형그래프
    @Query("SELECT o.productId.id, COUNT(o.productId.id) " +
            "FROM Orderitem o " +
            "JOIN o.productId p " +
            "JOIN p.seller s \n" +
//            "WHERE s.id = 'seller01' \n" +
            "GROUP BY o.productId.id")
    List<Object[]> findsellProduct();

    @Query("SELECT o " +
            "FROM Orderitem o " +
            "JOIN o.productId p " +
            "JOIN o.order_list ol " +
            "JOIN ol.buyer b " +
            "WHERE o.orderitemPstatus LIKE '결제전'")
    List<Object[]> findoutstanding();
}