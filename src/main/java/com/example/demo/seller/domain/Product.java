package com.example.demo.seller.domain;

import com.example.demo.buyer.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private String productId;

    private String PRODUCT_NAME;
    private String PRODUCT_EXPLAIN;
    private int PRODUCT_PRICE;
    private int PRODUCT_DISCOUNT;
    private String PRODUCT_HASHTAG;
    private int PRODUCT_ACTIVATION;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "SELLER_ID", referencedColumnName = "SELLER_ID")
    private Seller SELLER_ID;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
    private Category CATEGORY_ID;

}
