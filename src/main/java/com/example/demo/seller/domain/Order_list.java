package com.example.demo.seller.domain;

import com.example.demo.admin.Entity.Buyer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Order_list {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ORDER_LIST_ID;

    private String BUYER_LIST_ID;

    private int ORDER_LIST_TPRICE;

    @Temporal(TemporalType.DATE)
    private LocalDate ORDER_LIST_DATE;

    private String ORDER_LIST_ADDRESS;

    @ManyToOne
    @JoinColumn(name = "BUYER_LIST_ID", referencedColumnName = "BUYER_ID", insertable = false, updatable = false)
    private Buyer buyer;
}
