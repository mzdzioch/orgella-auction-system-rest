package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService implements IBidService{

    @Autowired
    BidRepository bidRepository;

    @Override
    public Bid saveBid(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public Optional<List<Bid>> findAllByAuction(Auction auction) {
        return bidRepository.findAllByAuction(auction);
    }
}
