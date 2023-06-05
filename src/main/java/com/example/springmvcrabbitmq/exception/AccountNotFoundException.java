package com.example.springmvcrabbitmq.exception;

public class AccountNotFoundException extends NotFoundException{
    public AccountNotFoundException(Long accountId) {
        super(String.format("User with %s id could not found ",accountId));
    }
}
