package com.example.springmvcrabbitmq.dto.response;

import com.example.springmvcrabbitmq.model.Account;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class AccountDtoForResponse {

    private String accountNumber;

    private BigDecimal balance;

    private String userName;

    public static AccountDtoForResponse fromAccount(Account account) {
        AccountDtoForResponse accaountDto = new AccountDtoForResponse();
        accaountDto.setAccountNumber(account.getAccountNumber());
        accaountDto.setBalance(account.getBalance());
        accaountDto.setUserName(account.getUser().getName());
        return accaountDto;
    }
}
