package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.dto.BidDto;
import com.orgella.model.dto.CreateAuctionDto;

import java.util.List;
import java.util.Optional;

public interface IAuctionService {

    Optional<Auction> tryCreateAuction(CreateAuctionDto auctionDto);

    Optional<Bid> tryMakeBid(BidDto bidDto);

    Optional<List<Auction>> getAllAuctionsWithLatestPrice();
}
