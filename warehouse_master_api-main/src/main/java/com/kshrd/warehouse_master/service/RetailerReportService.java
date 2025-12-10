package com.kshrd.warehouse_master.service;

import com.kshrd.warehouse_master.model.retailer.report.RetailerReport;

import java.text.ParseException;

public interface RetailerReportService {
    RetailerReport getRetailerMonthlyReport(String startDate, String endDate) throws ParseException;

//    RetailerReport getRetailerMonthlyReport(Timestamp startDate, Timestamp endDate);


}
