package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.Person;
import com.orgella.model.dto.CreateAuctionDto;
import com.orgella.model.factory.AuctionFactory;
import com.orgella.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService implements IAuctionService{


    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    IBidService bidService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IPersonService personService;

    @Override
    public Auction saveAuction(Auction auction){
        return auctionRepository.save(auction);
    }


    @Override
    public Optional<Auction> tryCreateAuction(CreateAuctionDto auctionDto) {
        Auction createdAuction;
        Optional<Person> personCreatingAuction;

        //check if login exists
        personCreatingAuction = personService.findPersonByLogin(auctionDto.getLogin());

        if(!personCreatingAuction.isPresent()){
            return Optional.empty();
        }

        //check if category exists
        if(categoryService.isCategoryExist(auctionDto.getCategory())){
            return Optional.empty();
        }

        createdAuction = AuctionFactory.create(auctionDto, personCreatingAuction.get());

        return Optional.ofNullable(auctionRepository.save(createdAuction));
    }

//    @Override
//    public boolean tryMakeWinningBid(Auction auction, BidDto bidDto) {
//
//
//    }

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

//    @Override
//    public boolean isBidHigher(Auction auction, BigDecimal bidValue) {
//
//
//    }
//
//    public Optional<List<Bid>> getBidList(Auction auction){
//
//    }


    private BigDecimal getLastPrice(Auction auction){
        List<Bid> bidList = bidService.getBidList(auction).get();

        if(bidList.isEmpty())
            return auction.getPrice();

        return bidList.get(bidList.size() - 1).getBidPrice();
    }
}
