package com.example.springmvcrabbitmq.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDtoForRequest {

    @NotNull
    @Size(min = 3,max = 12,message = "Username must be between 3 and 12 characters long")
    private String username;



    @Email(message = "Invalid email format")
    private String email;
}
