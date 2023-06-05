package com.example.springmvcrabbitmq.dto.response;

import com.example.springmvcrabbitmq.model.Transfer;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class TransferDtoForResponse {

    private Long id;

    private String sender;

    private String recipient;

    private BigDecimal amount;


    public static TransferDtoForResponse fromTransfer(Transfer transfer) {
        TransferDtoForResponse transferDto = new TransferDtoForResponse();
        transferDto.setId(transfer.getId());
        transferDto.setSender(transfer.getSender().getName());
        transferDto.setRecipient(transfer.getRecipient().getName());
        transferDto.setAmount(transfer.getAmount());
        return transferDto;
    }
}
