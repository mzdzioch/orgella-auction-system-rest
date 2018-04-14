package com.orgella.controller;

import com.orgella.model.Auction;
import com.orgella.model.dto.AuctionDto;
import com.orgella.model.response.ResponseMessage;
import com.orgella.service.AuctionService;
import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/auction/")
@CrossOrigin
public class AuctionsController {

    @Autowired
    AuctionService auctionService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseMessage<Auction> addAuction(@RequestBody AuctionDto auctionDto){
        Optional<Auction> addAuction = auctionService.saveAuction();

    }
}
