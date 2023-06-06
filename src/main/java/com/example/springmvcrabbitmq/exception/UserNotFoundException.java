package com.example.springmvcrabbitmq.exception;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(Long id) {
        super(String.format("User with %s id could not found ",id));
    }

    public UserNotFoundException(String name) {
        super("User could not found this name : " + name);
    }
}
