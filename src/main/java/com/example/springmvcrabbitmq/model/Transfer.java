package com.example.springmvcrabbitmq.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "transfers")
@Data
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender; // Gönderici

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient; // Alıcı

    private BigDecimal amount; // Miktar


}
