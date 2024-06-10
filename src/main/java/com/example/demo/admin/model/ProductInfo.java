package com.example.demo.admin.model;

import org.springframework.stereotype.Component;

@Component
public class ProductInfo {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
