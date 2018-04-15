package com.orgella.model.factory;

import com.orgella.model.Bid;

import java.util.ArrayList;
import java.util.List;

public class BidFactory {

    public static List<Bid> createEmptyBidList(){
        List<Bid> bidList = new ArrayList<>();
        return bidList;
    }
}
