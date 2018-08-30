package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.dto.BidDto;
import com.orgella.repository.AuctionRepository;
import com.orgella.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BidService implements IBidService{

    public static final int MAX_NUMBER_OF_BIDS = 3;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Override
    public Bid saveBid(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public Optional<List<Bid>> findAllByAuction(Auction auction) {
        return bidRepository.findAllByAuction(auction);
    }

    @Override
    public boolean tryMakeWinningBid(Auction auction, BidDto bidDto) {

        List<Bid> bidList = getBidList(auction).get();

        if(bidList.size() < MAX_NUMBER_OF_BIDS) {
            bidRepository.save(new Bid(bidDto.getBidPrice(), auction, auction.getPerson()));
            return false;
        } else if(bidList.size() == MAX_NUMBER_OF_BIDS) {
            bidRepository.save(new Bid(bidDto.getBidPrice(), auction, auction.getPerson()));
            setAuctionFalse(auction);
            return true;
        } else return false;
    }

    @Override
    public Boolean isBidHigher(Auction auction, BigDecimal bidValue) {

        List<Bid> bidList = getBidList(auction).get();

        if(!bidList.isEmpty()) {
            if(bidValue.compareTo(bidList.get(bidList.size() - 1).getBidPrice()) == 1) {
                return true;
            }
            return false;
        } else if(bidValue.compareTo(auction.getPrice()) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<List<Bid>> getBidList(Auction auction) {
        return bidRepository.findAllByAuction(auction);
    }

    private void setAuctionFalse(Auction auction){
        auction.setActive(false);
        auctionRepository.save(auction);
    }
}
