package com.orgella.controller;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import com.orgella.model.Person;
import com.orgella.model.dto.BidDto;
import com.orgella.model.dto.CreateAuctionDto;
import com.orgella.model.response.ResponseMessage;
import com.orgella.model.response.StatusResponse;
import com.orgella.service.AuctionService;
import com.orgella.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/auction/")
@CrossOrigin
public class AuctionController {

    @Autowired
    AuctionService auctionService;

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseMessage<Auction> addAuction(@RequestBody CreateAuctionDto auctionDto){
        Optional<Auction> addAuction = auctionService.tryCreateAuction(auctionDto);

        if(addAuction.isPresent()){
            return new ResponseMessage<>(StatusResponse.OK, null, addAuction.get());
        }

        return new ResponseMessage<>(StatusResponse.REQUEST_ERROR, "Failed to add auction.", null);
    }

    @RequestMapping(value = "/auctionlist", method = RequestMethod.GET)
    public ResponseMessage<List<Auction>> getAuctionsList(){
        Optional<List<Auction>> auctionList = auctionService.getAllAuctions();

        return new ResponseMessage<>(
                StatusResponse.OK,
                null,
                auctionList.get()
        );
    }


    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public ResponseMessage<List<Auction>> getAuctions(){
        Optional<List<Auction>> auctionList = auctionService.getAllAuctionsWithLatestPrice();

        return new ResponseMessage<>(
                StatusResponse.OK,
                null,
                auctionList.get()
        );
    }

    @RequestMapping(value = "/getactive", method = RequestMethod.GET)
    public ResponseMessage<List<Auction>> getActiveAuctions(){
        Optional<List<Auction>> auctionList = auctionService.getAllActiveAuctionsWithLatestPrice();

        return new ResponseMessage<>(
                StatusResponse.OK,
                null,
                auctionList.get()
        );
    }


    @RequestMapping(value = "/getactive/{login}", method = RequestMethod.GET)
    public ResponseMessage<List<Auction>> getActiveAuctionsNotPerson(@RequestParam String login){
        Optional<Person> person = personService.findPersonByLogin(login);
        Optional<List<Auction>> auctionList = auctionService.getAllAuctionsWithLatestPriceAndPersonIsNot(person.get());

        return new ResponseMessage<>(
                StatusResponse.OK,
                null,
                auctionList.get()
        );
    }

    @RequestMapping(value = "/getinactive", method = RequestMethod.GET)
    public ResponseMessage<List<Auction>> getInactiveAuctions(){
        Optional<List<Auction>> auctionList = auctionService.getAllInactiveAuctionsWithLatestPrice();

        return new ResponseMessage<>(
                StatusResponse.OK,
                null,
                auctionList.get()
        );
    }

    @RequestMapping(value = "/getauction/{id}", method = RequestMethod.GET)
    public ResponseMessage<Auction> getAuction(@PathVariable("id") Integer id){
        Optional<Auction> auction = auctionService.getAuction(id);

        if(auction.isPresent()){
            return new ResponseMessage<>(StatusResponse.OK, null, auction.get());
        }

        return new ResponseMessage<>(StatusResponse.REQUEST_ERROR, "No auction with this id.", null);
    }






}
