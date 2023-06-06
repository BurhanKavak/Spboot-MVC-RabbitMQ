package com.example.springmvcrabbitmq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class AlreadyExistException extends NotFoundException{
    public AlreadyExistException(String message) {
        super(message);
    }
}
