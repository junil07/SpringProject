package com.example.demo.buyer.DTO;

import org.springframework.stereotype.Component;

@Component
public class BuyerDTO {

    private String buyerId;
    private String buyerPassword;

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
}
