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
