package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Order_list {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_LIST_ID")
    private Integer order_list_Id;

    @Column(name = "ORDER_LIST_TPRICE")
    private int order_list_Tprice;

    @Column(name = "ORDER_LIST_DATE")
    private LocalDate order_list_Date;

    @Column(name = "ORDER_LIST_ADDRESS")
    private String order_list_Address;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "BUYER_LIST_ID", referencedColumnName = "BUYER_ID")
    private Buyer buyer;

    public Order_list(){

    }
    public Order_list(String order_list_Address, LocalDate order_list_Date, int order_list_Tprice,
                      Buyer buyer){
        this.order_list_Address=order_list_Address;
        this.order_list_Date=order_list_Date;
        this.order_list_Tprice=order_list_Tprice;
        this.buyer=buyer;
    }

    public Integer getOrder_list_Id() {
        return order_list_Id;
    }

    public void setOrder_list_Id(Integer order_list_Id) {
        this.order_list_Id = order_list_Id;
    }

    public int getOrder_list_Tprice() {
        return order_list_Tprice;
    }

    public void setOrder_list_Tprice(int order_list_Tprice) {
        this.order_list_Tprice = order_list_Tprice;
    }

    public LocalDate getOrder_list_Date() {
        return order_list_Date;
    }

    public void setOrder_list_Date(LocalDate order_list_Date) {
        this.order_list_Date = order_list_Date;
    }

    public String getOrder_list_Address() {
        return order_list_Address;
    }

    public void setOrder_list_Address(String order_list_Address) {
        this.order_list_Address = order_list_Address;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
