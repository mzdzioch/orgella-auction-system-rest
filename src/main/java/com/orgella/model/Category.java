package com.orgella.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Category parent;

    //@JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE}, mappedBy = "parent") //(fetch = FetchType.EAGER, mappedBy = "parent", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Category> children = new ArrayList<>();

    public Category() {
    }

    public Category(String categoryName, Category parent) {
        this.categoryName = categoryName;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }


}
