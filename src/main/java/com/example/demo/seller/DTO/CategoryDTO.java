package com.example.demo.seller.DTO;

import java.util.List;

public class CategoryDTO {

    private Long categoryId;
    private String categoryName;
    private int categoryDef;
    private Long categoryParentId;
    private List<CategoryDTO> children;

    // 기본 생성자
    public CategoryDTO() {
    }

    // 생성자
    public CategoryDTO(Long categoryId, String categoryName, int categoryDef, Long categoryParentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDef = categoryDef;
        this.categoryParentId = categoryParentId;
    }

    // getters and setters
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryDef() {
        return categoryDef;
    }

    public void setCategoryDef(int categoryDef) {
        this.categoryDef = categoryDef;
    }

    public Long getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Long categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public List<CategoryDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDTO> children) {
        this.children = children;
    }
}
