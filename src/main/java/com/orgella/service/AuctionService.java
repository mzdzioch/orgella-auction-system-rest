package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.Person;
import com.orgella.model.dto.BidDto;
import com.orgella.model.dto.CreateAuctionDto;
import com.orgella.model.factory.AuctionFactory;
import com.orgella.repository.AuctionRepository;
import com.orgella.repository.BidRepository;
import com.orgella.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService implements IAuctionService{

    public static final int MAX_NUMBER_OF_BIDS = 3;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public Auction saveAuction(Auction auction){
        return auctionRepository.save(auction);
    }


    @Override
    public Optional<Auction> tryCreateAuction(CreateAuctionDto auctionDto) {

        //check if login exists
        Optional<Person> personCreatingAuction = personRepository.findPersonByLogin(auctionDto.getLogin());

        if(!personCreatingAuction.isPresent()){
            return Optional.empty();
        }

        Auction createdAuction = AuctionFactory.create(auctionDto, personCreatingAuction.get());
        createdAuction = auctionRepository.save(createdAuction);

        return Optional.ofNullable(createdAuction);
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
    public Optional<List<Auction>> getAllAuctions(){
        return auctionRepository.getAllBy();
    }

    @Override
    public Optional<List<Auction>> getAllAuctionsWithLatestPrice(){

        Optional<List<Auction>> tempList = auctionRepository.getAllBy();

        tempList.get().forEach(
                a -> a.setPrice(getLastPrice(a))
        );

        return tempList;
    }

    @Override
    public Optional<List<Auction>> getAllActiveAuctionsWithLatestPrice(){

        Optional<List<Auction>> tempList = auctionRepository.findAuctionsByActiveIsTrue();

        tempList.get().forEach(
                a -> a.setPrice(getLastPrice(a))
        );

        return tempList;
    }

    @Override
    public Optional<List<Auction>> getAllInactiveAuctionsWithLatestPrice(){

        Optional<List<Auction>> tempList = auctionRepository.findAuctionsByActiveIsFalse();

        tempList.get().forEach(
                a -> a.setPrice(getLastPrice(a))
        );

        return tempList;
    }

    @Override
    public Optional<List<Auction>> getAllAuctionsWithLatestPriceAndPersonIsNot(Person person){

        Optional<List<Auction>> tempList = auctionRepository.findAuctionsByActiveIsTrueAndPersonIsNot(person);

        tempList.get().forEach(
                a -> a.setPrice(getLastPrice(a))
        );

        return tempList;
    }


    @Override
    public Optional<List<Auction>> findAllAuctionsByPerson(Person person){

        Optional<List<Auction>> tempList = auctionRepository.findAllByPerson(person);

        tempList.get().forEach(
                a -> a.setPrice(getLastPrice(a))
        );

        return tempList;
    }

    @Override
    public Optional<Auction> getAuction(Integer id){
        Optional<Auction> auction = auctionRepository.findAuctionById(id);
        auction.get().setPrice(getLastPrice(auction.get()));
        return auction;
    }

    @Override
    public boolean isBidHigher(Auction auction, BigDecimal bidValue) {

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

    public Optional<List<Bid>> getBidList(Auction auction){
        return bidRepository.findAllByAuction(auction);
    }


    private BigDecimal getLastPrice(Auction auction){
        List<Bid> bidList = getBidList(auction).get();

        if(bidList.isEmpty())
            return auction.getPrice();

        return bidList.get(bidList.size() - 1).getBidPrice();
    }


    private void setAuctionFalse(Auction auction){
        auction.setActive(false);
        auctionRepository.save(auction);
    }

}
