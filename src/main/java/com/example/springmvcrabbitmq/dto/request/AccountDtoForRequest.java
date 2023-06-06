package com.example.springmvcrabbitmq.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class AccountDtoForRequest {

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than 0")
    private BigDecimal balance;

    @NotNull(message = "User ID is required")
    private Long userId;

}
