package com.example.demo.seller.DTO;

import java.time.LocalDate;

public class OrderitemDTO {

    private Integer orderitemId;
    private String orderitemPstatus;
    private String orderitemDstatus;
    private int orderitemPcount;
    private int orderitemPrice;
    private LocalDate orderitemDate;
    private String orderitemCase;

    public OrderitemDTO(){

    }

    public OrderitemDTO(Integer orderitemId, String orderitemPstatus, String orderitemDstatus,
                        int orderitemPcount, int orderitemPrice, LocalDate orderitemDate, String orderitemCase){
        this.orderitemId = orderitemId;
        this.orderitemPstatus = orderitemPstatus;
        this.orderitemDstatus = orderitemDstatus;
        this.orderitemPcount = orderitemPcount;
        this.orderitemPrice = orderitemPrice;
        this.orderitemDate = orderitemDate;
        this.orderitemCase = orderitemCase;
    }

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

    public LocalDate getOrderitemDate() {
        return orderitemDate;
    }

    public void setOrderitemDate(LocalDate orderitemDate) {
        this.orderitemDate = orderitemDate;
    }

    public String getOrderitemCase() {
        return orderitemCase;
    }

    public void setOrderitemCase(String orderitemCase) {
        this.orderitemCase = orderitemCase;
    }
}
