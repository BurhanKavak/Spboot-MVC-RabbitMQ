package com.example.springmvcrabbitmq.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;

    private String recipient;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
