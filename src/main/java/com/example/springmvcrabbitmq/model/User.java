package com.example.springmvcrabbitmq.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Account> accounts;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Transfer> sentTransfers;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private List<Transfer> receivedTransfers;

}
