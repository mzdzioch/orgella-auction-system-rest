package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.Person;
import com.orgella.repository.AuctionRepository;
import com.orgella.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    BidRepository bidRepository;

    public List<Auction> getAllAuctions(){
        return auctionRepository.getAllBy();
    }

    public List<Auction> getAllAuctionsWithLatesPrice(){
        List<Auction> tempList;

        tempList = auctionRepository.findAuctionsByActiveIsTrue();

        tempList.forEach(
                a -> a.setPrice(getLastPrice(a))
        );

        return tempList;
    }

    public List<Auction> getAllAuctionsWithLatesPriceAndPersonIsNot(Person person){
        List<Auction> tempList;

        tempList = auctionRepository.findAuctionsByActiveIsTrueAndPersonIsNot(person);

        tempList.forEach(
                a -> a.setPrice(getLastPrice(a))
        );

        return tempList;
    }

    public List<Auction> getAllActiveAuctions(){
        return auctionRepository.findAuctionsByActiveIsTrue();
    }

    public List<Auction> getAllInactiveAuctions(){
        return auctionRepository.findAuctionsByActiveIsFalse();
    }

    public List<Auction> findAllAuctionsByPerson(Person person){
        return auctionRepository.findAllByPerson(person);
    }

    public Auction getAuction(Integer id){
        return auctionRepository.findAuctionById(id);
    }

    public Auction saveAuction(Auction auction){
        return auctionRepository.save(auction);
    }

    public List<Bid> getBidList(Auction auction){
        return bidRepository.findAllByAuction(auction);
    }


    public BigDecimal getLastPrice(Auction auction){
        List<Bid> bidList = new ArrayList<>();
        bidList = getBidList(auction);

        if(bidList.isEmpty())
            return auction.getPrice();

        return bidList.get(bidList.size()-1).getBidPrice();
    }

    public boolean isBidHigher(Auction auction, BigDecimal bidValue) {

        List<Bid> bidList = new ArrayList<>();
        bidList = getBidList(auction);

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

    public boolean makeBid(Auction auction, BigDecimal bidValue) {

        List<Bid> bidList = new ArrayList<>();
        bidList = getBidList(auction);

        if(bidList.size() < 3) {
            bidRepository.save(new Bid(bidValue, auction, auction.getPerson()));
            return true;
        }

        return false;
    }

    public void setAuctionFalse(Auction auction){
        auction.setActive(false);
        auctionRepository.save(auction);
    }
}
