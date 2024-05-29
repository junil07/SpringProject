package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orderitem")
public class Orderitem {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDERITEM_ID")
    private Integer orderitemId;
    @Column(name="ORDERITEM_PSTATUS")
    private String orderitemPstatus;
    @Column(name="ORDERITEM_DSTATUS")
    private String orderitemDstatus;
    @Column(name="ORDERITEM_PCOUNT")
    private int orderitemPcount;
    @Column(name="ORDERITEM_PRICE")
    private int orderitemPrice;

    // Order_list 엔티티와의 매핑
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ORDER_LIST_ID", referencedColumnName = "order_list_Id")
    private Order_list order_list;

    // Product 엔티티와의 매핑
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "productId")
    private Product productId;
}

