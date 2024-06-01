package com.example.demo.buyer.DTO;

public class ProductOrderSummaryDTO {
    private Integer productId;
    private String productCode;
    private String productName;
    private int productPrice;
    private int totalCount;
    private String productImageName;
    private String productImageExtension;

    public ProductOrderSummaryDTO(Integer productId, String productCode,String productName, int productPrice, int totalCount, String productImageName, String productImageExtension) {
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalCount = totalCount;
        this.productImageName = productImageName;
        this.productImageExtension = productImageExtension;
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

    public String getProductImageName() {
        return productImageName;
    }

    public void setProductImageName(String productImageName) {
        this.productImageName = productImageName;
    }

    public String getProductImageExtension() {
        return productImageExtension;
    }

    public void setProductImageExtension(String productImageExtension) {
        this.productImageExtension = productImageExtension;
    }
}
