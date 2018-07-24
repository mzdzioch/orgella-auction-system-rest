package com.orgella.repository;

import com.orgella.model.Auction;
import com.orgella.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends CrudRepository<Auction, Integer> {

    Optional<List<Auction>> getAllBy();

    Optional<List<Auction>> findAuctionsByActiveIsTrue();

    Optional<List<Auction>> findAuctionsByActiveIsFalse();

    Optional<List<Auction>> findAuctionsByActiveIsTrueAndPersonIsNot(Person person);

    Optional<Auction> findAuctionById(Integer id);

    Optional<List<Auction>> findAllByPerson(Person person);

    Optional<List<Auction>> findAllByCategoryId(Integer categoryId);


}
