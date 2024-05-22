package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Orderitem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ORDERITEM_ID;

    // Order_list 엔티티와의 매핑
    @ManyToOne
    @JoinColumn(name = "ORDER_LIST_ID", referencedColumnName = "ORDER_LIST_ID")
    private Order_list order_list;

    // Product 엔티티와의 매핑
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private Product product;

    private String ORDERITEM_PSTATUS;
    private String ORDERITEM_DSTATUS;
    private int ORDERITEM_PCOUNT;
    private int ORDERITEM_PRICE;

}

