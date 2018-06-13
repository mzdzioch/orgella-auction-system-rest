package com.orgella.model.dto;

import java.math.BigDecimal;

public class BidDto {

    private BigDecimal bidPrice;
    private Integer auctionId;
    private String login;

    public BidDto() {
    }

    public BidDto(BigDecimal bidPrice, Integer auctionId, String login) {
        this.bidPrice = bidPrice;
        this.auctionId = auctionId;
        this.login = login;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
