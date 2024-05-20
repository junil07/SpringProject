package com.example.demo.seller.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String BUYER_ID;

    private String BUYER_PASSWORD;
    private Date BUYER_BIRTH;
    private String BUYER_ADDRESS;
    private String BUYER_PHONENUM;
    private String BUYER_EMAIL;
    private String BUYER_NAME;
    private int BUYER_ACTIVATION;
    private Date BUYER_LASTLOGIN;
    private int BUYER_GRADE;

    // 생성자, Getter 및 Setter는 생략
}
