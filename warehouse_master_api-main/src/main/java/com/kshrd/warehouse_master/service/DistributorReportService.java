package com.kshrd.warehouse_master.service;

import com.kshrd.warehouse_master.controller.distributor.report.DistributorReportController;
import com.kshrd.warehouse_master.model.report.DistributorReport;

import java.text.ParseException;

public interface DistributorReportService {
    DistributorReport getDistributorReport(String startDate, String endDate) throws ParseException;
}
