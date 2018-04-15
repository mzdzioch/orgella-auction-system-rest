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

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService implements IAuctionService{

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public Optional<Auction> tryCreateAuction(CreateAuctionDto auctionDto) {

        //sprawdzamy czy istnieje login
        Optional<Person> personCreatingAuction = personRepository.findPersonByLogin(auctionDto.getLogin());

        if(!personCreatingAuction.isPresent()){
            return Optional.empty();
        }

        Auction createdAuction = AuctionFactory.create(auctionDto, personCreatingAuction.get());
        createdAuction = auctionRepository.save(createdAuction);

        return Optional.ofNullable(createdAuction);
    }

    @Override
    public Optional<Bid> tryMakeBid(BidDto bidDto) {
        return Optional.empty();
    }



    public List<Auction> getAllAuctions(){
        return auctionRepository.getAllBy();
    }

    public Optional<List<Auction>> getAllAuctionsWithLatestPrice(){

        Optional<List<Auction>> tempList = auctionRepository.findAuctionsByActiveIsTrue();

        tempList.get().forEach(
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

    public Optional<Auction> getAuction(Integer id){
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
