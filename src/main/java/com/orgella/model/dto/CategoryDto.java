package com.orgella.model.dto;

public class CategoryDto {

    private String categoryName;
    private String parentName;

    public CategoryDto() {
    }

    public CategoryDto(String categoryName, String parentName) {
        this.categoryName = categoryName;
        this.parentName = parentName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
