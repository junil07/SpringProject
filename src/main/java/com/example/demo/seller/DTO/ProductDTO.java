package com.example.demo.seller.DTO;

public class ProductDTO {

    private String productId;
    private String productName;
    private String productExplain;
    private int productPrice;
    private int productDiscount;
    private String productHashtag;
    private int productActivation;
    private String sellerId; // 판매자 ID
    private Long categoryId; // 카테고리 ID

    // 기본 생성자
    public ProductDTO() {
    }

    // 생성자
    public ProductDTO(String productId, String productName, String productExplain,
                      int productPrice, int productDiscount, String productHashtag,
                      int productActivation, String sellerId, Long categoryId) {
        this.productId = productId;
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
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
