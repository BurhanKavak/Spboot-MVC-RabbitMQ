package com.example.springmvcrabbitmq.service.impl;

import com.example.springmvcrabbitmq.dto.request.AccountDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.AccountDtoForResponse;
import com.example.springmvcrabbitmq.dto.response.UserDtoForResponse;
import com.example.springmvcrabbitmq.model.Account;
import com.example.springmvcrabbitmq.model.User;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.repository.AccountRepository;
import com.example.springmvcrabbitmq.service.AccountService;
import com.example.springmvcrabbitmq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final UserService userService;

    @Override
    public ApiResponse<List<AccountDtoForResponse>> getAccounts() {
        List<Account> accountList = accountRepository.findAll();

        List<AccountDtoForResponse> accountDtoList = new ArrayList<>(accountList.size());
        for (Account account :
                accountList) {
            AccountDtoForResponse accountDto = AccountDtoForResponse.fromAccount(account);
            accountDtoList.add(accountDto);
        }
        return ApiResponse.default_OK(accountDtoList);
    }

    @Override
    public ApiResponse<AccountDtoForResponse> getOneAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));
        AccountDtoForResponse accountDto = AccountDtoForResponse.fromAccount(account);
        return ApiResponse.default_OK(accountDto);
    }

    @Override
    public ApiResponse<AccountDtoForResponse> createAccount(AccountDtoForRequest accountDto) {
        UserDtoForResponse userDto = userService.getOneUser(accountDto.getUserId()).getData();
        Account account = new Account();

        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBalance(accountDto.getBalance());
        User user = userDto.toUser();
        account.setUser(user);

        accountRepository.save(account);

        AccountDtoForResponse createdAccountDto = AccountDtoForResponse.fromAccount(account);

        return ApiResponse.default_CREATED(createdAccountDto);

    }

    @Override
    public ApiResponse<AccountDtoForResponse> withdrawMoney(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);

        AccountDtoForResponse accountDto = AccountDtoForResponse.fromAccount(account);
        return ApiResponse.default_OK(accountDto);
    }

    @Override
    public void decreaseBalance(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));

        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) { // Bakiye 0'dan küçükse yetersiz bakiye hatası fırlat
            throw new RuntimeException("Insufficient balance");

        }

        account.setBalance(newBalance);
        accountRepository.save(account);

    }

    @Override
    public void increaseBalance(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));


        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }


}
