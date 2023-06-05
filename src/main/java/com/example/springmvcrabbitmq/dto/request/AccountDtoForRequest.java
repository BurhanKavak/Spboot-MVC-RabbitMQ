package com.example.springmvcrabbitmq.dto.request;


import com.example.springmvcrabbitmq.dto.response.UserDtoForResponse;
import com.example.springmvcrabbitmq.model.User;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.service.UserService;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class AccountDtoForRequest {

    private String accountNumber;

    private BigDecimal balance;

    private Long userId;

}
