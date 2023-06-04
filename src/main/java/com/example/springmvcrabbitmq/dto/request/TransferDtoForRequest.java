package com.example.springmvcrabbitmq.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDtoForRequest {

    private String sender;

    private String recipient;

    private BigDecimal amount;

    private Long userId;
}
