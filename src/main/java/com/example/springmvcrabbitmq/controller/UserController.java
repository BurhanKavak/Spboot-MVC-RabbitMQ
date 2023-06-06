package com.example.springmvcrabbitmq.controller;

import com.example.springmvcrabbitmq.dto.request.UserDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.UserDtoForResponse;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;


    @GetMapping
    public ApiResponse<List<UserDtoForResponse>> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserDtoForResponse> getOneUser(@PathVariable("userId") Long userId) {
        return userService.getOneUser(userId);
    }

    @PostMapping
    public ApiResponse<UserDtoForResponse> createUser(@Valid @RequestBody UserDtoForRequest userDto) {
        return userService.createUser(userDto);
    }
}
