package com.kshrd.warehouse_master.controller.distributor.DistributorHomepage;

import com.kshrd.warehouse_master.model.ApiResponse;
import com.kshrd.warehouse_master.model.appUser.AppUser;
import com.kshrd.warehouse_master.model.distributor.DistributorHomepage;
import com.kshrd.warehouse_master.model.order.OrderChartByMonth;
import com.kshrd.warehouse_master.service.DistributorHomepageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Tag(name = "Distributor Homepage Controller")
@RequestMapping("${base.distributor.v1}order_activities")
@SecurityRequirement(name = "bearer")
public class DistributorHomepageController {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "MM-dd")
    private Date endDate;

   private final DistributorHomepageService distributorHomepageService;

    public DistributorHomepageController(DistributorHomepageService distributorHomepageService) {
        this.distributorHomepageService = distributorHomepageService;
    }

    @GetMapping("")
    public ResponseEntity<?> getNewOrder(){
        AppUser appUser= (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer  currentUserId= appUser.getId();

        ApiResponse<DistributorHomepage> response= ApiResponse.<DistributorHomepage>builder()
                .status(201)
                .message("fetched successfully")
                .data(distributorHomepageService.getNewOrder(currentUserId))
                .date(formatter.format(date= new Date()))
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/months")
    @Operation(summary = "Get total orders and products sold sort by months (YYYY-MM)")
    public ResponseEntity<?> getTotalByMonth(@RequestParam(defaultValue = "2023-01") String startDate,@RequestParam(defaultValue = "2023-05") String endDated) throws ParseException {

        AppUser appUser= (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer  currentUserId= appUser.getId();

        ApiResponse<OrderChartByMonth> response= ApiResponse.<OrderChartByMonth>builder()
                .status(201)
                .message("fetched successfully")
                .data(distributorHomepageService.getTotalByMonth(currentUserId,startDate,endDated))
                .date(formatter.format(date= new Date()))
                .build();
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/years")
//    @Operation(summary = "Get total orders and products sold sort by Years (YYYY-MM)")
//    public ResponseEntity<?> getTotalByYear(@RequestParam(defaultValue = "2020-01") String startDate,@RequestParam(defaultValue = "2023-01") String endDated) throws ParseException {
//
//        System.out.println(endDated);
//
//        AppUser appUser= (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Integer  currentUserId= appUser.getId();
//
//        ApiResponse<OrderChartByYear> response= ApiResponse.<OrderChartByYear>builder()
//                .status(201)
//                .message("fetched successfully")
//                .data(distributorHomepageService.getTotalByYear(currentUserId,startDate,endDated))
//                .date(formatter.format(date= new Date()))
//                .build();
//        return ResponseEntity.ok(response);
//    }

}
