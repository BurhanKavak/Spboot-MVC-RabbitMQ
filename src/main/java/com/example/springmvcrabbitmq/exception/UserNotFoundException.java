package com.example.springmvcrabbitmq.exception;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(Long id) {
        super(String.format("User with %s id could not found ",id));
    }
}
