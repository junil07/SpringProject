package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Buyer {

    @Id
    @Column(name = "BUYER_ID")
    private String buyerId;
    @Column(name = "BUYER_PASSWORD")
    private String buyerPassword;
    @Column(name = "BUYER_BIRTH")
    private LocalDate buyerBirth;
    @Column(name = "BUYER_ADDRESS")
    private String buyerAddress;
    @Column(name = "BUYER_PHONENUM")
    private String buyerPhonenum;
    @Column(name = "BUYER_EMAIL")
    private String buyerEmail;
    @Column(name = "BUYER_NAME")
    private String buyerName;
    @Column(name = "BUYER_ACTIVATION")
    private int buyerActivation;
    @Column(name = "BUYER_LASTLOGIN")
    private LocalDate buyerLastlogin;
    @Column(name = "BUYER_GRADE")
    private int buyerGrade;

    public Buyer(){
        this.buyerId = UUID.randomUUID().toString();
    }

    public Buyer(String buyerPassword, LocalDate buyerBirth, String buyerAddress, String buyerPhonenum,
                 String buyerEmail, String buyerName, int buyerActivation, LocalDate buyerLastlogin, int buyerGrade){
        this.buyerPassword=buyerPassword;
        this.buyerBirth=buyerBirth;
        this.buyerAddress=buyerAddress;
        this.buyerPhonenum=buyerPhonenum;
        this.buyerEmail=buyerEmail;
        this.buyerName=buyerName;
        this.buyerActivation=buyerActivation;
        this.buyerLastlogin=buyerLastlogin;
        this.buyerGrade=buyerGrade;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerPassword() {
        return buyerPassword;
    }

    public void setBuyerPassword(String buyerPassword) {
        this.buyerPassword = buyerPassword;
    }

    public LocalDate getBuyerBirth() {
        return buyerBirth;
    }

    public void setBuyerBirth(LocalDate buyerBirth) {
        this.buyerBirth = buyerBirth;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerPhonenum() {
        return buyerPhonenum;
    }

    public void setBuyerPhonenum(String buyerPhonenum) {
        this.buyerPhonenum = buyerPhonenum;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public int getBuyerActivation() {
        return buyerActivation;
    }

    public void setBuyerActivation(int buyerActivation) {
        this.buyerActivation = buyerActivation;
    }

    public LocalDate getBuyerLastlogin() {
        return buyerLastlogin;
    }

    public void setBuyerLastlogin(LocalDate buyerLastlogin) {
        this.buyerLastlogin = buyerLastlogin;
    }

    public int getBuyerGrade() {
        return buyerGrade;
    }

    public void setBuyerGrade(int buyerGrade) {
        this.buyerGrade = buyerGrade;
    }
}
