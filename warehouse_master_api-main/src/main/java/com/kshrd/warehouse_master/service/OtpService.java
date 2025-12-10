package com.kshrd.warehouse_master.service;

public interface OtpService {
    String generateOtp(String email);
    String verifyOtp(Integer otp, String email);

}
