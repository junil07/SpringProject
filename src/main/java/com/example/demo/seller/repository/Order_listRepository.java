package com.example.demo.seller.repository;

import com.example.demo.seller.domain.Orderlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_listRepository extends JpaRepository<Orderlist, Integer> {

    @Query("SELECT o FROM Orderlist o LEFT JOIN FETCH o.buyer")
    List<Orderlist> findAllWithBuyer();

}