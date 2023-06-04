package com.example.springmvcrabbitmq.service.impl;

import com.example.springmvcrabbitmq.dto.request.TransferDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.TransferDtoForResponse;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.repository.TransferRepository;
import com.example.springmvcrabbitmq.service.AccountService;
import com.example.springmvcrabbitmq.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {


    private final TransferRepository transferRepository;

    private final AccountService accountService;


    @Override
    public ApiResponse<TransferDtoForResponse> createTransfer(TransferDtoForRequest transferDto) {
        return null;
    }
}
