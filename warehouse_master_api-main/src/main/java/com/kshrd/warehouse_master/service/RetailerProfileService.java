package com.kshrd.warehouse_master.service;

import com.kshrd.warehouse_master.model.retailer.Retailer;
import com.kshrd.warehouse_master.model.retailer.RetailerRequest;

import java.text.ParseException;

public interface RetailerProfileService {
    Retailer createRetailerProfile(Integer currentUserId, RetailerRequest retailerRequest);
    Retailer getRetailerProfile(Integer currentUserId) throws ParseException;
    Retailer updateRetailerProfile(Integer currentUserId, RetailerRequest retailerRequest);
}
