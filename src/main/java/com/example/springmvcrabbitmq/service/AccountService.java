package com.example.springmvcrabbitmq.service;

import com.example.springmvcrabbitmq.dto.request.AccountDtoForRequest;
import com.example.springmvcrabbitmq.dto.request.TransferDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.AccountDtoForResponse;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    ApiResponse<List<AccountDtoForResponse>> getAccounts();

    ApiResponse<AccountDtoForResponse> getOneAccount(Long accountId);

    ApiResponse<AccountDtoForResponse> createAccount(AccountDtoForRequest accountDto);

    ApiResponse<AccountDtoForResponse> withdrawMoney(Long accountId, BigDecimal amount); // Para çekme metodu

    void decreaseBalance(Long accountId, BigDecimal amount);  // Bakiyeyi azaltma metodu
    void increaseBalance(Long accountId, BigDecimal amount); // Bakiyeyi artırma metodu

   // void increaseBalanceFromMessage(TransferDtoForRequest transferDto);
}
