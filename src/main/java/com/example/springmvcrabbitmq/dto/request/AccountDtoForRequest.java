package com.example.springmvcrabbitmq.dto.request;


import lombok.Data;

import java.math.BigDecimal;
@Data
public class AccountDtoForRequest {

    private String accountNumber;

    private BigDecimal balance;

    private Long userId;
}
