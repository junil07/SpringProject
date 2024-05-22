package com.example.demo.buyer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categoryPath")
public class CategoryPath {

    @Id
    private Long categoryId;

    private String categoryName;

    private Integer categoryDef;

    private String categoryPath;

}
