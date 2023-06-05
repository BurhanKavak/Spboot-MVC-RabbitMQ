package com.example.springmvcrabbitmq.service.impl;

import com.example.springmvcrabbitmq.dto.request.TransferDtoForRequest;
import com.example.springmvcrabbitmq.dto.response.TransferDtoForResponse;
import com.example.springmvcrabbitmq.dto.response.UserDtoForResponse;
import com.example.springmvcrabbitmq.model.Account;
import com.example.springmvcrabbitmq.model.Transfer;
import com.example.springmvcrabbitmq.model.User;
import com.example.springmvcrabbitmq.model.messages.ApiResponse;
import com.example.springmvcrabbitmq.repository.TransferRepository;
import com.example.springmvcrabbitmq.service.AccountService;
import com.example.springmvcrabbitmq.service.TransferService;
import com.example.springmvcrabbitmq.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {


    private final TransferRepository transferRepository;

    private final AccountService accountService;

    private final UserService userService;


    @Override
    public ApiResponse<TransferDtoForResponse> createTransfer(TransferDtoForRequest transferDto) {

        //TODO ismine göre değilde findByUserID yaparsan daha iyi olur çünkü benzersiz olması gerekiyor.
        //TODO bunu security kullanarak daha rahat yapabilirsin bir User giriş ekranı olsun Client tarafında

        User senderUser = userService.findByUsernameAndEmail(transferDto.getSender().getUsername(),
                transferDto.getSender().getEmail());

        User recipientUser = userService.findByUsernameAndEmail(transferDto.getRecipient().getUsername(),
                transferDto.getRecipient().getEmail());

        accountService.decreaseBalance(senderUser.getId(), transferDto.getAmount());

        accountService.increaseBalance(recipientUser.getId(), transferDto.getAmount());

        Transfer transfer = new Transfer();
        transfer.setSender(senderUser);
        transfer.setRecipient(recipientUser);
        transfer.setAmount(transferDto.getAmount());

        Transfer savedTransfer = transferRepository.save(transfer);
        TransferDtoForResponse transferResponse = TransferDtoForResponse.fromTransfer(savedTransfer);

        return ApiResponse.default_CREATED(transferResponse);
    }
}
