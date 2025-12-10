package com.kshrd.warehouse_master.service;

import com.kshrd.warehouse_master.model.distributor.Distributor;
import com.kshrd.warehouse_master.model.distributor.DistributorRequest;
import java.text.ParseException;

public interface DistributorProfileService {

       Distributor getUserProfile(Integer currentUserId) throws ParseException;

       Distributor addUserProfile(Integer currentUserId, DistributorRequest distributorRequest) throws ParseException;

       Distributor updateUserProfile(Integer currentUserId, DistributorRequest distributorRequest) throws ParseException;
}
