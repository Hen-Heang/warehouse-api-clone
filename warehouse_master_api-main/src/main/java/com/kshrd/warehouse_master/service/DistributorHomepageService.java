package com.kshrd.warehouse_master.service;
import com.kshrd.warehouse_master.model.distributor.DistributorHomepage;
import com.kshrd.warehouse_master.model.order.OrderChartByMonth;
import com.kshrd.warehouse_master.model.order.OrderChartByYear;

import java.text.ParseException;

public interface DistributorHomepageService {

    DistributorHomepage getNewOrder(Integer currentUserId);

    OrderChartByMonth getTotalByMonth(Integer currentUserId , String startDate, String endDate) throws ParseException;

//    OrderChartByYear getTotalByYear(Integer currentUserId, String startDate, String endDated) throws ParseException;
}
