package com.example.springmvcrabbitmq.controller;

import com.example.springmvcrabbitmq.dto.request.TransferDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.TransferDtoForResponse;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.service.TransferService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ApiResponse<TransferDtoForResponse> createTransfer(@RequestBody TransferDtoForRequest transferDto) {
        return transferService.createTransfer(transferDto);
    }

}
