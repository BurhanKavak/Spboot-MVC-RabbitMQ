package com.example.springmvcrabbitmq.exception;

public class InsufficientException extends RuntimeException{
    public InsufficientException(Long amount) {
        super(String.format("Insufficient Balance!!! : %s",amount));
    }
}
