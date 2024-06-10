package com.example.demo.buyer.DTO;

import com.example.demo.buyer.entity.Cart;

import java.util.List;

public class CartRequest {
    private String buyerId;
    private List<Cart> cartItems;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public List<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Cart> cartItems) {
        this.cartItems = cartItems;
    }
}
