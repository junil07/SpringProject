package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    @Column(name="ORDERITEM_DATE")
    private LocalDate orderitemDate;
    @Column(name="ORDERITEM_CASE")
    private String orderitemCase;


    // Order_list 엔티티와의 매핑
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ORDERLIST_ID", referencedColumnName = "ORDERLIST_ID")
    private Orderlist orderlist;

    // Product 엔티티와의 매핑
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "productId")
    private Product product;

    public Integer getOrderitemId() {
        return orderitemId;
    }

    public void setOrderitemId(Integer orderitemId) {
        this.orderitemId = orderitemId;
    }

    public String getOrderitemPstatus() {
        return orderitemPstatus;
    }

    public void setOrderitemPstatus(String orderitemPstatus) {
        this.orderitemPstatus = orderitemPstatus;
    }

    public String getOrderitemDstatus() {
        return orderitemDstatus;
    }

    public void setOrderitemDstatus(String orderitemDstatus) {
        this.orderitemDstatus = orderitemDstatus;
    }

    public int getOrderitemPcount() {
        return orderitemPcount;
    }

    public void setOrderitemPcount(int orderitemPcount) {
        this.orderitemPcount = orderitemPcount;
    }

    public int getOrderitemPrice() {
        return orderitemPrice;
    }

    public void setOrderitemPrice(int orderitemPrice) {
        this.orderitemPrice = orderitemPrice;
    }

    public Orderlist getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(Orderlist orderlist) {
        this.orderlist = orderlist;
    }

    public LocalDate getOrderitemDate() {
        return orderitemDate;
    }

    public void setOrderitemDate(LocalDate orderitemDate) {
        this.orderitemDate = orderitemDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getOrderitemCase() {
        return orderitemCase;
    }

    public void setOrderitemCase(String orderitemCase) {
        this.orderitemCase = orderitemCase;
    }
}

