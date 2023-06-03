package com.example.springmvcrabbitmq.service;

import com.example.springmvcrabbitmq.dto.response.UserDtoForResponse;
import com.example.springmvcrabbitmq.model.User;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;

import java.util.List;

public interface UserService {

    ApiResponse<List<UserDtoForResponse>> getUsers();

    ApiResponse<UserDtoForResponse> getOneUser(Long userId);
    ApiResponse<UserDtoForResponse> createUser(User user);
}
