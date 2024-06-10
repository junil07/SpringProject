package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productRelationId;

    private String productRelationCode;
    private String productRelationName;
    private int productRelationOne;
    private int productRelationTwo;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "productId")
    private Product product;

    // 기본 생성자
    public ProductRelation() {

    }

    // 생성자
    public ProductRelation(long productRelationId, String productRelationCode, String productRelationName,
                           int productRelationOne, int productRelationTwo, Product product) {
        this.productRelationId = productRelationId;
        this.productRelationCode = productRelationCode;
        this.productRelationName = productRelationName;
        this.productRelationOne = productRelationOne;
        this.productRelationTwo = productRelationTwo;
        this.product = product;
    }

    public long getProductRelationId() {
        return productRelationId;
    }

    public void setProductRelationId(long productRelationId) {
        this.productRelationId = productRelationId;
    }

    public String getProductRelationCode() {
        return productRelationCode;
    }

    public void setProductRelationCode(String productRelationCode) {
        this.productRelationCode = productRelationCode;
    }

    public String getProductRelationName() {
        return productRelationName;
    }

    public void setProductRelationName(String productRelationName) {
        this.productRelationName = productRelationName;
    }

    public int getProductRelationOne() {
        return productRelationOne;
    }

    public void setProductRelationOne(int productRelationOne) {
        this.productRelationOne = productRelationOne;
    }

    public int getProductRelationTwo() {
        return productRelationTwo;
    }

    public void setProductRelationTwo(int productRelationTwo) {
        this.productRelationTwo = productRelationTwo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
