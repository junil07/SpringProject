package com.example.demo.seller.domain;

import com.example.demo.admin.Entity.Buyer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Orderlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDERLIST_ID")
    private Integer orderlistId;

    @Column(name = "ORDERLIST_TPRICE")
    private int orderlistTprice;

    @Column(name = "ORDERLIST_DATE",nullable = false)
    private LocalDate orderlistDate = LocalDate.now();;

    @Column(name = "ORDERLIST_ADDRESS")
    private String orderlistAddress;



    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "BUYER_ID", referencedColumnName = "BUYER_ID")
    private Buyer buyer;

    public Orderlist(){

    }
    public Orderlist(String orderlistAddress, LocalDate orderlistDate, int orderlistTprice,
                     Buyer buyer){
        this.orderlistAddress = orderlistAddress;
        this.orderlistDate = orderlistDate;
        this.orderlistTprice = orderlistTprice;
        this.buyer=buyer;
    }

    public Integer getOrderlistId() {
        return orderlistId;
    }

    public void setOrderlistId(Integer orderlistId) {
        this.orderlistId = orderlistId;
    }

    public int getOrderlistTprice() {
        return orderlistTprice;
    }

    public void setOrderlistTprice(int orderlistTprice) {
        this.orderlistTprice = orderlistTprice;
    }

    public LocalDate getOrderlistDate() {
        return orderlistDate;
    }

    public void setOrderlistDate(LocalDate orderlistDate) {
        this.orderlistDate = orderlistDate;
    }

    public String getOrderlistAddress() {
        return orderlistAddress;
    }

    public void setOrderlistAddress(String orderlistAddress) {
        this.orderlistAddress = orderlistAddress;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
