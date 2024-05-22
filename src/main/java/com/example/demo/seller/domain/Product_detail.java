package com.example.demo.seller.domain;

import com.example.demo.buyer.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product_detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer PRODUCT_DETAIL_ID ;
    private String PRODUCT_DETAIL_MATE;
    private String PRODUCT_DETAIL_COLOR;
    private float PRODUCT_DETAIL_HEIGHT;
    private String PRODUCT_DETAIL_MAKER;
    private String PRODUCT_DETAIL_COUNTRY;
    private String PRODUCT_DETAIL_SIZE;
    private String PRODUCT_DETAIL_YEAR;
    private String PRODUCT_DETAIL_WARNING;
    private String PRODUCT_DETAIL_AS;
    private String PRODUCT_DETAIL_STANDARD;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "productId")
    private Product productId;

    //proudctId 문자열로 반환
}
