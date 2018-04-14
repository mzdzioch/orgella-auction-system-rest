package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {

    @Autowired
    BidRepository bidRepository;

    Bid saveBid(Bid bid){
        return bidRepository.save(bid);
    }

    List<Bid> findAllByAuction(Auction auction){
        return bidRepository.findAllByAuction(auction);
    }
}
