package com.orgella.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Bid {

    @Id
    @GeneratedValue
    private Integer id;

    private BigDecimal bidPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    private Auction auction;

    @ManyToOne
    private Person person;

    public Bid() {
    }

    public Bid(BigDecimal bidPrice, Auction auction, Person person) {
        this.auction = auction;
        this.person = person;
        this.bidPrice = bidPrice;
    }

    public Integer getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }
}
