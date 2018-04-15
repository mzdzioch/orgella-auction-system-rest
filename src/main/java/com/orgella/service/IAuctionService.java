package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.dto.CreateAuctionDto;

import java.util.Optional;

public interface IAuctionService {

    Optional<Auction> tryCreateAuction(CreateAuctionDto auctionDto);
}
