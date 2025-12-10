package com.kshrd.warehouse_master.service;


import com.kshrd.warehouse_master.model.appUser.AppUserDto;
import com.kshrd.warehouse_master.model.appUser.AppUserRequest;
import com.kshrd.warehouse_master.model.appUser.LoginResponse;
import com.kshrd.warehouse_master.model.jwt.JwtChangePasswordRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUserDetailsService{
    AppUserDto insertUser(AppUserRequest appUserRequest);

    boolean getVerifyEmail(String email);

    AppUserDto changePassword(JwtChangePasswordRequest request);

    String forgetPassword(Integer otp, String email, String newPassword);
}
