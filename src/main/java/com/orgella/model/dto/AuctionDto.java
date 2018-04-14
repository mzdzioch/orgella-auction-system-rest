package com.orgella.model.dto;

import com.orgella.model.Category;

import java.math.BigDecimal;

public class AuctionDto {

    private String title;
    private BigDecimal price;
    private int category;
    private String description;
    private String login;

    public AuctionDto() {
    }

    public AuctionDto(String title, BigDecimal price, int category, String description, String login) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.login = login;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
