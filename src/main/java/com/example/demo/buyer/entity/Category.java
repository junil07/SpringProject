package com.example.demo.buyer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id; // 카테고리 아이디
    private String category_name; // 카테고리 이름
    private int category_def; // 카테고리 깊이
    private int category_parent_id; // 카테고리 상위 아이디

}