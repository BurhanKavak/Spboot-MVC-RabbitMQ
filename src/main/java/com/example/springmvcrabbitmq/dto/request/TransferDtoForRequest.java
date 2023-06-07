package com.example.springmvcrabbitmq.dto.request;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TransferDtoForRequest implements Serializable {

    private UserDtoForRequest sender;

    private UserDtoForRequest recipient;


    private BigDecimal amount;

}
