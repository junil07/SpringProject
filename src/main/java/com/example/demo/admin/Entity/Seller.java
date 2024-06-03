package com.example.demo.admin.Entity;

import com.example.demo.seller.domain.Product;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @Column(name = "SELLER_ID", length = 40, nullable = false)
    private String sellerId;

    @Column(name = "SELLER_PASSWORD", length = 500, nullable = false)
    private String sellerPassword;

    @Column(name = "seller_birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sellerBirth;

    @Column(name = "SELLER_ADDRESS", length = 100, nullable = false)
    private String sellerAddress;

    @Column(name = "SELLER_PHONENUM", length = 20, nullable = false)
    private String sellerPhoneNum;

    @Column(name = "SELLER_BNUM", length = 45, nullable = false)
    private String sellerBnum;

    @Column(name = "SELLER_EMAIL", length = 45, nullable = false)
    private String sellerEmail;

    @Column(name = "SELLER_NAME", length = 45, nullable = false)
    private String sellerName;

    @Column(name = "seller_activation", nullable = true)
    private Short sellerActivation;

    @Column(name = "seller_lastlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sellerLastLogin;

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

    public Date getSellerBirth() {
        return sellerBirth;
    }

    public void setSellerBirth(Date sellerBirth) {
        this.sellerBirth = sellerBirth;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerPhoneNum() {
        return sellerPhoneNum;
    }

    public void setSellerPhoneNum(String sellerPhoneNum) {
        this.sellerPhoneNum = sellerPhoneNum;
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

    public Short getSellerActivation() {
        return sellerActivation;
    }

    public void setSellerActivation(Short sellerActivation) {
        this.sellerActivation = sellerActivation;
    }

    public Date getSellerLastLogin() {
        return sellerLastLogin;
    }

    public void setSellerLastLogin(Date sellerLastLogin) {
        this.sellerLastLogin = sellerLastLogin;
    }

}
