package com.example.springmvcrabbitmq.exception;

public class AccountNotFoundException extends NotFoundException{
    public AccountNotFoundException(Long accountId) {
        super(String.format("Account with %s id could not found ",accountId));
    }
}
