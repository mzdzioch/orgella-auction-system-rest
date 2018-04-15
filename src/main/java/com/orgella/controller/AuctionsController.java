package com.orgella.controller;

import com.orgella.model.Auction;
import com.orgella.model.dto.CreateAuctionDto;
import com.orgella.model.response.ResponseMessage;
import com.orgella.model.response.StatusResponse;
import com.orgella.service.AuctionService;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



}
