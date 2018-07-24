package com.orgella.service;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AuctionServiceTest {

    @Autowired
    AuctionService auctionService;

    @Autowired
    PersonService personService;

    @Autowired
    BidService bidService;

    @Before
    public void setUp() throws Exception {
        //boolean active, String title, BigDecimal price, int categoryID, String description, Person person
        auctionService.saveAuction(new Auction(true, "Nowy laptop", new BigDecimal(20), 4, "Sprzedam nowy laptop", personService.findPersonByLogin("misiek").get()));
        auctionService.saveAuction(new Auction(true, "Apple nowy", new BigDecimal(40), 4, "Mam nowy laptop apple", personService.findPersonByLogin("jacek").get()));
        auctionService.saveAuction(new Auction(true, "Opel Adam", new BigDecimal(120), 13, "Sprzedam opla. Nowy. Nieuzywany", personService.findPersonByLogin("michal").get()));
        auctionService.saveAuction(new Auction(true, "Opel Astra", new BigDecimal(200), 13, "Sprzedam nowy samochod", personService.findPersonByLogin("misiek").get()));
        auctionService.saveAuction(new Auction(false, "Opel Astra", new BigDecimal(200), 13, "Sprzedam nowy samochod", personService.findPersonByLogin("jacek").get()));
    }

    @Test
    public void shouldGetAllAuctions() throws Exception {
        List<Auction> auctions = auctionService.getAllActiveAuctionsWithLatestPrice().get();

        Assert.notEmpty(auctions);

    }

    @Test
    public void shouldReturnTrueIfGetAllFourActiveAuctions() throws Exception {

        List<Auction> auctions = auctionService.getAllActiveAuctionsWithLatestPrice().get();

        assertTrue(auctions.size() == 4);
    }

    @Test
    public void shouldReturnTrueIfGetOneInactiveAuctinon() throws Exception {
        List<Auction> auctions = auctionService.getAllInactiveAuctionsWithLatestPrice().get();

        assertTrue(auctions.size() == 1);
    }

    @Test
    public void shouldReturnTrueIfAuctionIsSaved() throws Exception {

        Auction auction = auctionService.saveAuction(new Auction(true, "Nowy PC", new BigDecimal(21), 5, "Sprzedam nowy PC", personService.findPersonByLogin("misiek").get()));

        assertNotNull(auction);

    }

//    @Test
//    public void shouldReturnTrueIfReceiveLastPriceAsOriginalPrice() throws Exception {
//        List<Auction> auctionList = auctionService.getAllActiveAuctionsWithLatestPrice().get();
//
//
//        assertTrue(auctionService.getLastPrice(auctionList.get(0)).equals(new BigDecimal(20)));
//    }

//    @Test
//    public void shouldReturnTrueIfReceiveLastBidPrice() throws Exception {
//
//        List<Auction> auctionList = auctionService.getAllActiveAuctionsWithLatestPrice().get();
//        List<Bid> bidList = new ArrayList<>();
//        Bid bid = new Bid(new BigDecimal(21), auctionList.get(0), auctionList.get(0).getPerson());
//
//        bidList.add(bid);
//        bidService.saveBid(bid);
//
//        auctionList.get(0).setBidList(bidList);
//
//
//        assertTrue(auctionService.getLastPrice(auctionList.get(0)).equals(new BigDecimal(21)));
//    }

    @Test
    public void shouldReturnTrueIfBidIsHigherThan() throws Exception {

        Auction auction = auctionService.getAuction(1).get();

        assertTrue(auctionService.isBidHigher(auction, new BigDecimal(21)));
    }

    @Test
    public void shouldReturnNullForListBids() throws Exception {
        Auction auction2 = auctionService.getAuction(2).get();
        List<Bid> bidList = auctionService.getBidList(auction2);

        assertNull(bidList);
    }

    @Test
    public void shouldReturnForListBids() throws Exception {

        List<Bid> bidList = auctionService.getBidList(null);

        assertNull(bidList);
    }

    @Test
    public void validateBid() throws Exception {
    }

}