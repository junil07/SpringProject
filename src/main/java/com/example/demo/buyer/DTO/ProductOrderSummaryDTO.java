package com.example.demo.buyer.DTO;

public class ProductOrderSummaryDTO {
    private Integer productId;
    private String productCode;
    private String productName;
    private int productDiscount;
    private int productPrice;
    private int totalCount;
    private String productImageSname;
    private String productImageExtension;
    private String productDetailMaker;

    public String getProductDetailMaker() {
        return productDetailMaker;
    }

    public void setProductDetailMaker(String productDetailMaker) {
        this.productDetailMaker = productDetailMaker;
    }

    public int getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(int productDiscount) {
        this.productDiscount = productDiscount;
    }

    public ProductOrderSummaryDTO(Integer productId, String productCode, String productName,int productDiscount ,int productPrice, int totalCount, String productImageSname, String productImageExtension, String productDetailMaker) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.totalCount = totalCount;
        this.productImageSname = productImageSname;
        this.productImageExtension = productImageExtension;
        this.productDetailMaker = productDetailMaker;
    }

    // Getters and setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCode(){return productCode;}

    public void setProductCode(String productCode){this.productCode = productCode;}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getProductImageSname() {
        return productImageSname;
    }

    public void setProductImageSname(String productImageSname) {
        this.productImageSname = productImageSname;
    }

    public String getProductImageExtension() {
        return productImageExtension;
    }

    public void setProductImageExtension(String productImageExtension) {
        this.productImageExtension = productImageExtension;
    }
}
