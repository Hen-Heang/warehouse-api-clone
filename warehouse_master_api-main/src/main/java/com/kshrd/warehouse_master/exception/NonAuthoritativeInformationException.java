package com.kshrd.warehouse_master.exception;

public class NonAuthoritativeInformationException extends RuntimeException{
    public NonAuthoritativeInformationException(String message){
        super(message);
    }
}
