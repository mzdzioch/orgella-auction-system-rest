package com.orgella.model.dto;

import java.math.BigDecimal;

public class CreateAuctionDto {

    private String title;
    private BigDecimal price;
    private String category;   //TODO private String categoryName;
    private String description;
    private String login;

    public CreateAuctionDto() {
    }

    public CreateAuctionDto(String title, BigDecimal price, String category, String description, String login) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
