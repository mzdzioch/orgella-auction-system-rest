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

    public List<Auction> getAllBy();

    public List<Auction> findAuctionsByActiveIsTrue();

    public List<Auction> findAuctionsByActiveIsFalse();

    public List<Auction> findAuctionsByActiveIsTrueAndPersonIsNot(Person person);

    public Auction findAuctionById(Integer id);

    public List<Auction> findAllByPerson(Person person);

    public List<Auction> findAllByCategoryId(Integer categoryId);


}
