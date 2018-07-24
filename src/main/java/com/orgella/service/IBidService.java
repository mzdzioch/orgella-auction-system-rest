package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;

import java.util.List;
import java.util.Optional;

public interface IBidService {

    Bid saveBid(Bid bid);

    Optional<List<Bid>> findAllByAuction(Auction auction);
}
