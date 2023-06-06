package com.example.springmvcrabbitmq.controller;

import com.example.springmvcrabbitmq.dto.request.AccountDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.AccountDtoForResponse;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;

    }

    @GetMapping
    public ApiResponse<List<AccountDtoForResponse>> getAccounts() {
        return accountService.getAccounts();
    }


    @GetMapping("/{accountId}")
    public ApiResponse<AccountDtoForResponse> getOneAccount(@Valid @PathVariable("accountId") Long accountId) {
        return accountService.getOneAccount(accountId);
    }

    @PostMapping
    public ApiResponse<AccountDtoForResponse> createAccount(@Valid @RequestBody AccountDtoForRequest accountDto) {
        return accountService.createAccount(accountDto);
    }

    @PostMapping("/{accountId}/withdraw")
    public ApiResponse<AccountDtoForResponse> withdrawMoney(@Valid @PathVariable("accountId") Long accountId,@RequestParam BigDecimal amount) {
            return accountService.withdrawMoney(accountId,amount);
    }

}
