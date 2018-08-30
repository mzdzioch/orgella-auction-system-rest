package com.orgella.controller;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.dto.BidDto;
import com.orgella.model.response.ResponseMessage;
import com.orgella.model.response.StatusResponse;
import com.orgella.service.IAuctionService;
import com.orgella.service.IBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/bids/")
@CrossOrigin
public class BidController {

    @Autowired
    IBidService bidService;

    @Autowired
    IAuctionService auctionService;

    @RequestMapping(value = "/makebid", method = RequestMethod.POST)
    public ResponseMessage<Bid> makeBid(@RequestBody BidDto bidDto){

        Optional<Auction> auction = auctionService.getAuction(bidDto.getAuctionId());

        if(!bidService.isBidHigher(auction.get(), bidDto.getBidPrice())){
            return new ResponseMessage<>(StatusResponse.LOW_BID, "Your bid is low than current price! Try again", null);
        }

        Boolean isWinner = bidService.tryMakeWinningBid(auction.get(), bidDto);

        if(isWinner){
            return new ResponseMessage<>(StatusResponse.OK, null, null);
        }

        return new ResponseMessage<>(StatusResponse.UNSUCCESSFUL, "Make a bid again", null);

    }

}
