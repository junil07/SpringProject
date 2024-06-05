package com.example.demo.seller.domain;


import com.example.demo.admin.Entity.Seller;

import com.example.demo.buyer.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "PRODUCT_EXPLAIN")
    private String productExplain;
    @Column(name = "PRODUCT_PRICE")
    private int productPrice;
    @Column(name = "PRODUCT_DISCOUNT")
    private int productDiscount;
    @Column(name = "PRODUCT_HASHTAG")
    private String productHashtag;
    @Column(name = "product_activation")
    private int productActivation;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "sellerId")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "categoryId")
    private Category category;

    // 기본 생성자
    public Product() {
        //this.productId = UUID.randomUUID().toString(); // UUID를 사용해 고유한 ID 생성
    }

    // 모든 필드를 초기화하는 생성자
    public Product(long productId, String productCode, Seller seller, String productName, String productExplain,
                   int productPrice, int productDiscount, String productHashtag, Category category,
                   int productActivation) {
        this.productId = productId;
        this.productCode = productCode;
        this.seller = seller;
        this.productName = productName;
        this.productExplain = productExplain;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productHashtag = productHashtag;
        this.category = category;
        this.productActivation = productActivation;


    }
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) { this.productId = productId; }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductExplain() {
        return productExplain;
    }

    public void setProductExplain(String productExplain) {
        this.productExplain = productExplain;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(int productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductHashtag() {
        return productHashtag;
    }

    public void setProductHashtag(String productHashtag) {
        this.productHashtag = productHashtag;
    }

    public int getProductActivation() {
        return productActivation;
    }

    public void setProductActivation(int productActivation) {
        this.productActivation = productActivation;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}

