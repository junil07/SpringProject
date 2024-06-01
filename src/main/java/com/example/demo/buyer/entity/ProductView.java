package com.example.demo.buyer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_view")
public class ProductView {
    @Id
    private Integer productId;
    private String sellerId;
    private String productName;
    private String productExplain;
    private int productPrice;
    private int productDiscount;
    private String productHashtag;
    private int categoryId;
    private int productDetailId;
    private String productDetailMate;
    private String productDetailColor;
    private float productDetailHeight;
    private String productDetailMaker;
    private String productDetailCountry;
    private String productDetailSize;
    private String productDetailYear;
    private String productDetailWaring;
    private String productDetailAs;
    private String productDetailStandard;
    private String productImageSname;
    private String productImageName;
    private String productImageExtension;
    private int productImageSize;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getProductDetailMate() {
        return productDetailMate;
    }

    public void setProductDetailMate(String productDetailMate) {
        this.productDetailMate = productDetailMate;
    }

    public String getProductDetailColor() {
        return productDetailColor;
    }

    public void setProductDetailColor(String productDetailColor) {
        this.productDetailColor = productDetailColor;
    }

    public float getProductDetailHeight() {
        return productDetailHeight;
    }

    public void setProductDetailHeight(float productDetailHeight) {
        this.productDetailHeight = productDetailHeight;
    }

    public String getProductDetailMaker() {
        return productDetailMaker;
    }

    public void setProductDetailMaker(String productDetailMaker) {
        this.productDetailMaker = productDetailMaker;
    }

    public String getProductDetailCountry() {
        return productDetailCountry;
    }

    public void setProductDetailCountry(String productDetailCountry) {
        this.productDetailCountry = productDetailCountry;
    }

    public String getProductDetailSize() {
        return productDetailSize;
    }

    public void setProductDetailSize(String productDetailSize) {
        this.productDetailSize = productDetailSize;
    }

    public String getProductDetailYear() {
        return productDetailYear;
    }

    public void setProductDetailYear(String productDetailYear) {
        this.productDetailYear = productDetailYear;
    }

    public String getProductDetailWaring() {
        return productDetailWaring;
    }

    public void setProductDetailWaring(String productDetailWaring) {
        this.productDetailWaring = productDetailWaring;
    }

    public String getProductDetailAs() {
        return productDetailAs;
    }

    public void setProductDetailAs(String productDetailAs) {
        this.productDetailAs = productDetailAs;
    }

    public String getProductDetailStandard() {
        return productDetailStandard;
    }

    public void setProductDetailStandard(String productDetailStandard) {
        this.productDetailStandard = productDetailStandard;
    }

    public String getProductImageSname() {
        return productImageSname;
    }

    public void setProductImageSname(String productImageSname) {
        this.productImageSname = productImageSname;
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

    public int getProductImageSize() {
        return productImageSize;
    }

    public void setProductImageSize(int productImageSize) {
        this.productImageSize = productImageSize;
    }
}
