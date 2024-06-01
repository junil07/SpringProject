package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="product_image")
@Getter
@Setter
public class ProductImage {
    @Id
    private String productImageSname;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID",referencedColumnName = "productId")
    private Product product;

    private String productImageName;
    private String productImageExtension;
    private int productImageSize;

    public String getProductImageSname() {
        return productImageSname;
    }

    public void setProductImageSname(String productImageSname) {
        this.productImageSname = productImageSname;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
