package com.orgella.model.factory;

import com.orgella.model.Auction;
import com.orgella.model.Person;
import com.orgella.model.dto.CreateAuctionDto;

public class AuctionFactory {
    public static Auction create(CreateAuctionDto dto, Person person){
        return new Auction(
                dto.getTitle(),
                dto.getPrice(),
                dto.getCategory(),
                dto.getDescription(),
                person);
    }
}
