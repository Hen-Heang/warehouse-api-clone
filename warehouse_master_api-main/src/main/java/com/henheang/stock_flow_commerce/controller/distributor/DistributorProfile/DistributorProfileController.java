package com.henheang.stock_flow_commerce.controller.distributor.DistributorProfile;

import com.henheang.stock_flow_commerce.model.ApiResponse;
import com.henheang.stock_flow_commerce.model.appUser.AppUser;
import com.henheang.stock_flow_commerce.model.distributor.Distributor;
import com.henheang.stock_flow_commerce.model.distributor.DistributorRequest;
import com.henheang.stock_flow_commerce.service.DistributorProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Tag(name = "Distributor Profile Controller")
@RequestMapping("${base.distributor.v1}profiles")
@SecurityRequirement(name = "bearer")

public class DistributorProfileController {

     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date;

    private final DistributorProfileService userProfileService;


    public DistributorProfileController(DistributorProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
    @Operation(summary = "Get user profile")
    @GetMapping("/")
    public ResponseEntity<?> getUserProfileById() throws ParseException {
        AppUser appUser=(AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId= appUser.getId();
        return ResponseEntity.ok( new ApiResponse<>(
                HttpStatus.OK.value(),
                "Fetched successfully.",
                userProfileService.getUserProfile(currentUserId),
                formatter.format(date= new Date())
        ));

    }
    @Operation(summary = "Create profile")
    @PostMapping("/")
    public ResponseEntity<?> addUserProfile(@RequestBody DistributorRequest distributorRequest) throws ParseException {
        AppUser appUser= (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId= appUser.getId();
        ApiResponse<Distributor> response = ApiResponse.<Distributor>builder()
                .status(HttpStatus.CREATED.value())
                .message("User profile added")
                .data(userProfileService.addUserProfile(currentUserId, distributorRequest))
                .date(formatter.format(date=new Date()))
                .build();
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(summary = "Update profile")
    @PutMapping("/")
    public ResponseEntity<?> updateUserProfile(@RequestBody DistributorRequest distributorRequest) throws ParseException {
        AppUser appUser= (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId= appUser.getId();
        ApiResponse<Distributor> response = ApiResponse.<Distributor>builder()
                .status(HttpStatus.OK.value())
                .message("successfully updated")
                .data(userProfileService.updateUserProfile(currentUserId, distributorRequest))
                .date(formatter.format(date = new Date()))
                .build();
        return ResponseEntity.ok(response);
    }

}
