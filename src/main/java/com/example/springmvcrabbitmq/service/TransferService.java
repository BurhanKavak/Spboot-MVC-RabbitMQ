package com.example.springmvcrabbitmq.service;

import com.example.springmvcrabbitmq.dto.request.TransferDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.TransferDtoForResponse;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;

public interface TransferService {

    ApiResponse<TransferDtoForResponse> createTransfer(TransferDtoForRequest transferDto);
}
