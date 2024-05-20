package com.example.demo.seller.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Seller {

    @Id
    private String SELLER_ID;

    private String SELLER_PASSWORD;
    @Temporal(TemporalType.DATE)
    private LocalDate SELLER_BIRTH;
    private String SELLER_ADDRESS;
    private String SELLER_PHONENUM;
    private String SELLER_BNUM;
    private String SELLER_EMAIL;
    private String SELLER_NAME;
    private int SELLER_ACTIVATION;
    private String SELLER_LASTLOGIN;

}
