package com.example.springmvcrabbitmq.service.impl;

import com.example.springmvcrabbitmq.dto.response.UserDtoForResponse;
import com.example.springmvcrabbitmq.model.User;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.repository.UserRepository;
import com.example.springmvcrabbitmq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public ApiResponse<List<UserDtoForResponse>> getUsers() {
        List<User> userList = userRepository.findAll();

        List<UserDtoForResponse> userDtoList = new ArrayList<>();
        for (User user :
                userList) {
            UserDtoForResponse userDto = UserDtoForResponse.fromUser(user);
            userDtoList.add(userDto);
        }
        return ApiResponse.default_OK(userDtoList);
    }

    @Override
    public ApiResponse<UserDtoForResponse> createUser(User user) {
        userRepository.save(user);
        UserDtoForResponse userDtoForResponse = UserDtoForResponse.fromUser(user);
        return ApiResponse.default_CREATED(userDtoForResponse);

    }
}
