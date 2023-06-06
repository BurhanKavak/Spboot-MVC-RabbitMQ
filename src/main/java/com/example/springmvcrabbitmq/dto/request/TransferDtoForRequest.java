package com.example.springmvcrabbitmq.dto.request;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDtoForRequest {

    private UserDtoForRequest sender;

    private UserDtoForRequest recipient;


    private BigDecimal amount;

}
