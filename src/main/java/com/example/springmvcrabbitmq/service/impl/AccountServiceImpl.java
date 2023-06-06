package com.example.springmvcrabbitmq.service.impl;

import com.example.springmvcrabbitmq.dto.request.AccountDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.AccountDtoForResponse;
import com.example.springmvcrabbitmq.exception.AccountNotFoundException;
import com.example.springmvcrabbitmq.exception.InsufficientException;
import com.example.springmvcrabbitmq.model.Account;
import com.example.springmvcrabbitmq.model.User;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.repository.AccountRepository;
import com.example.springmvcrabbitmq.service.AccountService;
import com.example.springmvcrabbitmq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
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
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        AccountDtoForResponse accountDto = AccountDtoForResponse.fromAccount(account);
        return ApiResponse.default_OK(accountDto);
    }

    @Override
    public ApiResponse<AccountDtoForResponse> createAccount(AccountDtoForRequest accountDto) {


        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBalance(accountDto.getBalance());

        User user = userService.findById(accountDto.getUserId());
        account.setUser(user);
        accountRepository.save(account);

        AccountDtoForResponse createdAccountDto = AccountDtoForResponse.fromAccount(account);
        return ApiResponse.default_CREATED(createdAccountDto);
    }


    @Override
    public ApiResponse<AccountDtoForResponse> withdrawMoney(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientException(accountId);
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
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        BigDecimal newBalance = account.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) { // Bakiye 0'dan küçükse yetersiz bakiye hatası fırlat
            throw new InsufficientException(accountId);

        }

        account.setBalance(newBalance);
        accountRepository.save(account);

    }

    @Override
    @RabbitListener(queues = "${sq.rabbit.queue.name}")
    public void increaseBalance(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }


}
