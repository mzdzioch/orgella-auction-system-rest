package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.dto.BidDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IBidService {

    Bid saveBid(Bid bid);

    Optional<List<Bid>> findAllByAuction(Auction auction);

    boolean tryMakeWinningBid(Auction auction, BidDto bidDto);

    Boolean isBidHigher(Auction auction, BigDecimal bidValue);

    Optional<List<Bid>> getBidList(Auction auction);


}
