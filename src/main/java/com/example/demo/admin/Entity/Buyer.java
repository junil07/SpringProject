package com.example.demo.admin.Entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Buyer {

    @Id
    @Column(name = "BUYER_ID")
    private String buyerId;
    @Column(name = "BUYER_PASSWORD")
    private String buyerPassword;
    @Column(name = "BUYER_NAME")
    private String buyerName;
    @Column(name = "BUYER_BIRTH")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd") // 자동으로 Date 타입으로 변환해주네? 개꿀 ㅋㅋㅋ
    private Date buyerBirth;
    @Column(name = "BUYER_EMAIL")
    private String buyerEmail;
    @Column(name = "BUYER_ADDRESS")
    private String buyerAddress;
    @Column(name = "BUYER_PHONENUM")
    private String buyerPhoneNum;
    @Column(name = "BUYER_ACTIVATION")
    private short buyerActivation;
    @Column(name = "BUYER_LASTLOGIN")
    @Temporal(TemporalType.DATE)
    private Date buyerLastLogin;
    @Column(name = "BUYER_GRADE")
    private short buyerGrade;

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

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public Date getBuyerBirth() {
        return buyerBirth;
    }

    public void setBuyerBirth(Date buyerBirth) {
        this.buyerBirth = buyerBirth;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerPhoneNum() {
        return buyerPhoneNum;
    }

    public void setBuyerPhoneNum(String buyerPhoneNum) {
        this.buyerPhoneNum = buyerPhoneNum;
    }

    public short getBuyerActivation() {
        return buyerActivation;
    }

    public void setBuyerActivation(short buyerActivation) {
        this.buyerActivation = buyerActivation;
    }

    public Date getBuyerLastLogin() {
        return buyerLastLogin;
    }

    public void setBuyerLastLogin(Date buyerLastLogin) {
        this.buyerLastLogin = buyerLastLogin;
    }

    public short getBuyerGrade() {
        return buyerGrade;
    }

    public void setBuyerGrade(short buyerGrade) {
        this.buyerGrade = buyerGrade;
    }
}
