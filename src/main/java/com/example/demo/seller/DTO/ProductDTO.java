package com.example.demo.seller.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {

    private long productId;
    private String productCode;
    private String productName;
    private String productExplain;
    private int productPrice;
    private int productDiscount;
    private List<String> productHashtag;
    private int productActivation;
    private String sellerId; // 판매자 ID
    private Long categoryId; // 카테고리 ID

    // 기본 생성자
    public ProductDTO() {
    }

    // 생성자
    public ProductDTO(long productId, String productCode, String productName, String productExplain,
                      int productPrice, int productDiscount, List<String> productHashtag,
                      int productActivation, String sellerId, Long categoryId) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.productExplain = productExplain;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.productHashtag = productHashtag;
        this.productActivation = productActivation;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
    }

    // 게터와 세터 메서드



    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public List<String> getProductHashtag() {
        return productHashtag;
    }

    public void setProductHashtag(List<String> productHashtag) {
        this.productHashtag = productHashtag;
    }

    public int getProductActivation() {
        return productActivation;
    }

    public void setProductActivation(int productActivation) {
        this.productActivation = productActivation;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

