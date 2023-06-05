package com.example.springmvcrabbitmq.service;

import com.example.springmvcrabbitmq.dto.request.UserDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.UserDtoForResponse;
import com.example.springmvcrabbitmq.model.User;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;

import java.util.List;

public interface UserService {

    ApiResponse<List<UserDtoForResponse>> getUsers();

    ApiResponse<UserDtoForResponse> getOneUser(Long userId);

    User findById(Long userId);
    ApiResponse<UserDtoForResponse> createUser(UserDtoForRequest userDtoForRequest);

    User findByUsernameAndEmail(String name, String email);

    User findByAccountId(Long accountId);
}
