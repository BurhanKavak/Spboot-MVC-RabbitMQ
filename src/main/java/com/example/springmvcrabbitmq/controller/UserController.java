package com.example.springmvcrabbitmq.controller;

import com.example.springmvcrabbitmq.model.User;
import com.example.springmvcrabbitmq.repository.UserRepository;
import com.example.springmvcrabbitmq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


}
