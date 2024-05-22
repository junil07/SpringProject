package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderitemRepository extends JpaRepository<Orderitem, Integer> {

    @Query("SELECT ol.ORDER_LIST_DATE, SUM(o.ORDERITEM_PRICE)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.order_list ol \n" +
            "JOIN o.productid p \n" +
            "GROUP BY ol.ORDER_LIST_DATE\n")
    List<Object[]> findTotalPricePerDate();

    @Query("SELECT ol.ORDER_LIST_DATE, COUNT(DISTINCT ol.BUYER_LIST_ID)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.order_list ol \n" +
            "JOIN o.productid p \n" +
            "GROUP BY ol.ORDER_LIST_DATE\n")
    List<Object[]> findBuyerDate();

    @Query("SELECT o.order_list.ORDER_LIST_ID, COUNT(o.order_list.ORDER_LIST_ID)\n" +
            "FROM Orderitem o \n" +
            "JOIN o.order_list ol \n" +
            "JOIN o.productid p \n" +
            "GROUP BY o.order_list.ORDER_LIST_ID\n")
    List<Object[]> findCountDate();

//    @Query("SELECT o.product.id, COUNT(o.product.id) FROM Orderitem o JOIN o.product p GROUP BY o.product.id")
//    List<Object[]> findsellProduct();

}
