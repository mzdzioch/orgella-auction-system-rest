package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.Person;
import com.orgella.model.dto.BidDto;
import com.orgella.model.dto.CreateAuctionDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IAuctionService {

    Auction saveAuction(Auction auction);

    Optional<Auction> tryCreateAuction(CreateAuctionDto auctionDto);

    Optional<List<Auction>> getAllAuctions();

    Optional<List<Auction>> getAllAuctionsWithLatestPrice();

    Optional<List<Auction>> getAllActiveAuctionsWithLatestPrice();

    Optional<List<Auction>> getAllInactiveAuctionsWithLatestPrice();

    Optional<List<Auction>> getAllAuctionsWithLatestPriceAndPersonIsNot(Person person);

    Optional<List<Auction>> findAllAuctionsByPerson(Person person);

    Optional<Auction> getAuction(Integer id);
}
