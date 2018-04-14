package com.orgella.repository;

import com.orgella.model.Auction;
import com.orgella.model.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BidRepository extends CrudRepository<Bid, Integer> {

    List<Bid> findAllByAuction(Auction auction);

}
