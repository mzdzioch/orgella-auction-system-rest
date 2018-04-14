package com.orgella.model.factory;

import com.orgella.model.Auction;
import com.orgella.model.dto.AuctionDto;

public class AuctionFactory {
    public static Auction createAuction(AuctionDto auctionDto){
        return new Auction();
    }
}
