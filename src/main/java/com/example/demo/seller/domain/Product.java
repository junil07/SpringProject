package com.example.demo.seller.domain;

import com.example.demo.buyer.entity.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    private String PRODUCT_ID;

    @ManyToOne
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "SELLER_ID")
    private Seller seller;

    private String PRODUCT_NAME;
    private String PRODUCT_EXPLAIN;
    private int PRODUCT_PRICE;
    private int PRODUCT_DISCOUNT;
    private String PRODUCT_HASHTAG;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    private Category category;
}
