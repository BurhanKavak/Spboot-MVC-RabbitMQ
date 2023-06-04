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

    private String sender; // Gönderici

    private String recipient; // Alıcı

    private BigDecimal amount; // Miktar

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
