package com.orgella.controller;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.dto.BidDto;
import com.orgella.model.dto.CreateAuctionDto;
import com.orgella.model.response.ResponseMessage;
import com.orgella.model.response.StatusResponse;
import com.orgella.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/auction/")
@CrossOrigin
public class AuctionsController {

    @Autowired
    AuctionService auctionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseMessage<Auction> addAuction(@RequestBody CreateAuctionDto auctionDto){
        Optional<Auction> addAuction = auctionService.tryCreateAuction(auctionDto);

        if(addAuction.isPresent()){
            return new ResponseMessage<>(StatusResponse.OK, null, addAuction.get());
        }

        return new ResponseMessage<>(StatusResponse.REQUEST_ERROR, "Failed to add auction.", null);
    }


    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseMessage<List<Auction>> getAuctions(){
        List<Auction> auctionList = auctionService.getAllAuctions();

        return new ResponseMessage<>(
                StatusResponse.OK,
                null,
                auctionList
        );
    }

    @RequestMapping(value = "/getactive", method = RequestMethod.GET)
    public ResponseMessage<List<Auction>> getActiveAuctions(){
        List<Auction> auctionList = auctionService.getAllActiveAuctions();

        return new ResponseMessage<>(
                StatusResponse.OK,
                null,
                auctionList
        );
    }

    @RequestMapping(value = "/getinactive", method = RequestMethod.GET)
    public ResponseMessage<List<Auction>> getInactiveAuctions(){
        List<Auction> auctionList = auctionService.getAllInactiveAuctions();

        return new ResponseMessage<>(
                StatusResponse.OK,
                null,
                auctionList
        );
    }


    @RequestMapping(value = "/getauction/{id}", method = RequestMethod.GET)
    public ResponseMessage<Auction> getAuction(@PathParam("id") Integer id){
        Optional<Auction> auction = auctionService.getAuction(id);

        if(auction.isPresent()){
            return new ResponseMessage<>(StatusResponse.OK, null, auction.get());
        }

        return new ResponseMessage<>(StatusResponse.REQUEST_ERROR, "No auction with this id.", null);
    }

//    @RequestMapping(value = "/bid", method = RequestMethod.POST)
//    public ResponseMessage<Bid> makeBid(@RequestBody BidDto bidDto){
//        Optional<Bid> bid = auctionService.makeBid();
//
//        if(bid.isPresent()){
//            return new ResponseMessage<>(StatusResponse.OK, null, bid.get());
//        }
//
//        return new ResponseMessage<>(StatusResponse.REQUEST_ERROR, "", null);
//
//    }



}
