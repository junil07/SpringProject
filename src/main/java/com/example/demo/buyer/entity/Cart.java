package com.example.demo.buyer.entity;

import com.example.demo.admin.Entity.Buyer;
import com.example.demo.seller.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @Column(name="CART_PRODUCT_COUNT")
    private Integer cartProductCount;

    @Column(name="CART_PRODUCT_SIZE")
    private Integer cartProductSize;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name="BUYER_ID")
    private Buyer buyer;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartProductCount() {
        return cartProductCount;
    }

    public void setCartProductCount(Integer cartProductCount) {
        this.cartProductCount = cartProductCount;
    }

    public Integer getCartProductSize() {
        return cartProductSize;
    }

    public void setCartProductSize(Integer cartProductSize) {
        this.cartProductSize = cartProductSize;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
