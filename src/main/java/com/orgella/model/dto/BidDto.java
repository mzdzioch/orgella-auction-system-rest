package com.orgella.model.dto;

import java.math.BigDecimal;

public class BidDto {

    private BigDecimal bid;
    private String login;
    private Integer auctionId;

    public BidDto() {
    }

    public BidDto(BigDecimal bid, String login, Integer auctionId) {
        this.bid = bid;
        this.login = login;
        this.auctionId = auctionId;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }
}
