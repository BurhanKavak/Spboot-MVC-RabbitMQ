package com.example.springmvcrabbitmq.service.impl;

import com.example.springmvcrabbitmq.dto.request.UserDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.UserDtoForResponse;
import com.example.springmvcrabbitmq.exception.AlreadyExistException;
import com.example.springmvcrabbitmq.exception.UserNotFoundException;
import com.example.springmvcrabbitmq.model.User;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.repository.UserRepository;
import com.example.springmvcrabbitmq.service.UserService;
import jakarta.validation.Valid;
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
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    }

    @Override
    public ApiResponse<UserDtoForResponse> getOneUser(Long userId) {
        User user = findById(userId);
        UserDtoForResponse userDtoForResponse = UserDtoForResponse.fromUser(user);
        return ApiResponse.default_OK(userDtoForResponse);
    }


    public ApiResponse<UserDtoForResponse> createUser(@Valid UserDtoForRequest userDtoForRequest) {

        String email = userDtoForRequest.getEmail();

        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistException("Email already exists: " + email);
        }

        User user = new User();
        user.setName(userDtoForRequest.getUsername());
        user.setEmail(userDtoForRequest.getEmail());
        userRepository.save(user);

        UserDtoForResponse userDtoForResponse = UserDtoForResponse.fromUser(user);
        return ApiResponse.default_CREATED(userDtoForResponse);
    }

    @Override
    public User findByUsernameAndEmail(String name, String email) {
        return userRepository.findByNameAndEmail(name, email)
                .orElseThrow(() -> new UserNotFoundException(name));
    }

    @Override
    public User findByAccountId(Long accountId) {
        return userRepository.findByAccounts_Id(accountId)
                .orElseThrow(() -> new UserNotFoundException(accountId));
    }


}
