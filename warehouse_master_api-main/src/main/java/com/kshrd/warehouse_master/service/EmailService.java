package com.kshrd.warehouse_master.service;

public interface EmailService {
    String sendSimpleMail(String recipient, String msgBody, String subject);

//    String sendMailWithAttachment(Email email);
}
