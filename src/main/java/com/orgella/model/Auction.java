package com.orgella.model;

import com.orgella.model.factory.BidFactory;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
//@NamedQuery(name = "Auction.findAllByHighestPrice",
//            query = "SELECT ")
public class Auction {

    @Id
    @GeneratedValue
    private Integer id;

    private boolean active;

    private String title;

    private BigDecimal price;

    private String categoryName;

    private String description;

    @ManyToOne//(cascade = CascadeType.ALL)
    private Person person;

    @OneToMany(cascade = CascadeType.ALL) //(targetEntity=Bid.class, mappedBy="auction", fetch=FetchType.EAGER)
    private List<Bid> bidList;

    public Auction() {
    }

    public Auction(String title, BigDecimal price, String categoryName, String description, Person person) {
        this.active = true;
        this.title = title;
        this.price = price;
        this.categoryName = categoryName;
        this.description = description;
        this.person = person;
        this.bidList = BidFactory.createEmptyBidList();
    }


    public Integer getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Bid> getListBids() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

}