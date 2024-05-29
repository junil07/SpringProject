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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productImageSname;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID",referencedColumnName = "productId")
    private Product product;

    private String productImageName;
    private String productImageExtension;
    private int productImageSize;
}
