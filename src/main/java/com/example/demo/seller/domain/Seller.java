package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Seller {

    @Id
    private String sellerId;

    private String sellerPassword;
    private LocalDate sellerBirth;
    private String sellerAddress;
    private String sellerPhonenum;
    private String sellerBnum;
    private String sellerEmail;
    private String sellerName;
    private int sellerActivation;
    private String sellerLastlogin;

    // 기본 생성자
    public Seller() {
    }

    // 모든 필드를 초기화하는 생성자
    public Seller(String sellerId, String sellerPassword, LocalDate sellerBirth, String sellerAddress,
                  String sellerPhonenum, String sellerBnum, String sellerEmail, String sellerName,
                  int sellerActivation, String sellerLastlogin) {
        this.sellerId = sellerId;
        this.sellerPassword = sellerPassword;
        this.sellerBirth = sellerBirth;
        this.sellerAddress = sellerAddress;
        this.sellerPhonenum = sellerPhonenum;
        this.sellerBnum = sellerBnum;
        this.sellerEmail = sellerEmail;
        this.sellerName = sellerName;
        this.sellerActivation = sellerActivation;
        this.sellerLastlogin = sellerLastlogin;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerPassword() {
        return sellerPassword;
    }

    public void setSellerPassword(String sellerPassword) {
        this.sellerPassword = sellerPassword;
    }

    public LocalDate getSellerBirth() {
        return sellerBirth;
    }

    public void setSellerBirth(LocalDate sellerBirth) {
        this.sellerBirth = sellerBirth;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerPhonenum() {
        return sellerPhonenum;
    }

    public void setSellerPhonenum(String sellerPhonenum) {
        this.sellerPhonenum = sellerPhonenum;
    }

    public String getSellerBnum() {
        return sellerBnum;
    }

    public void setSellerBnum(String sellerBnum) {
        this.sellerBnum = sellerBnum;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getSellerActivation() {
        return sellerActivation;
    }

    public void setSellerActivation(int sellerActivation) {
        this.sellerActivation = sellerActivation;
    }

    public String getSellerLastlogin() {
        return sellerLastlogin;
    }

    public void setSellerLastlogin(String sellerLastlogin) {
        this.sellerLastlogin = sellerLastlogin;
    }
}
