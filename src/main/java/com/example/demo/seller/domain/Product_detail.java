package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product_detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productDetailId;

    private String productDetailMate;
    private String productDetailColor;
    private double productDetailHeight;
    private String productDetailMaker;
    private String productDetailCountry;
    private String productDetailSize;
    private String productDetailYear;
    private String productDetailWarning;
    private String productDetailAs;
    private String productDetailStandard;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "productId")
    private Product product;

    // 기본 생성자
    public Product_detail() {
    }

    // 생성자
    public Product_detail(String productDetailMate, String productDetailColor, double productDetailHeight, String productDetailMaker,
                          String productDetailCountry, String productDetailSize, String productDetailYear, String productDetailWarning,
                          String productDetailAs, String productDetailStandard, Product product) {
        this.productDetailMate = productDetailMate;
        this.productDetailColor = productDetailColor;
        this.productDetailHeight = productDetailHeight;
        this.productDetailMaker = productDetailMaker;
        this.productDetailCountry = productDetailCountry;
        this.productDetailSize = productDetailSize;
        this.productDetailYear = productDetailYear;
        this.productDetailWarning = productDetailWarning;
        this.productDetailAs = productDetailAs;
        this.productDetailStandard = productDetailStandard;
        this.product = product;
    }

    public Integer getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(Integer productDetailId) {
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

    public double getProductDetailHeight() {
        return productDetailHeight;
    }

    public void setProductDetailHeight(double productDetailHeight) {
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

    public String getProductDetailWarning() {
        return productDetailWarning;
    }

    public void setProductDetailWarning(String productDetailWarning) {
        this.productDetailWarning = productDetailWarning;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
