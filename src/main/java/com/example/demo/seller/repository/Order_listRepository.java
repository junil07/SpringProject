package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Order_list;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_listRepository extends JpaRepository<Order_list, Integer> {

    @Query("SELECT o FROM Order_list o LEFT JOIN FETCH o.buyer")
    List<Order_list> findAllWithBuyer();

    @Query("SELECT o.ORDER_LIST_DATE, SUM(o.ORDER_LIST_TPRICE) FROM Order_list o GROUP BY o.ORDER_LIST_DATE")
    List<Object[]> findTotalPricePerDate();
}
