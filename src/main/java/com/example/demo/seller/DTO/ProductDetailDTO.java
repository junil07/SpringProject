package com.example.demo.seller.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailDTO {

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
    private long productId; // Product 엔티티의 ID를 참조하기 위해 추가

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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    // 기본 생성자
    public ProductDetailDTO() {
    }

    // 생성자
    public ProductDetailDTO(Integer productDetailId, String productDetailMate, String productDetailColor, double productDetailHeight,
                            String productDetailMaker, String productDetailCountry, String productDetailSize, String productDetailYear,
                            String productDetailWarning, String productDetailAs, String productDetailStandard, long productId) {
        this.productDetailId = productDetailId;
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
        this.productId = productId;
    }
}
