package com.kshrd.warehouse_master.exception;

public class NotModifiedException extends RuntimeException{
    public NotModifiedException(String message){
        super(message);
    }
}
