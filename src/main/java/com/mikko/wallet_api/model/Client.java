package com.mikko.wallet_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @Column(nullable = false)
    String fullName;
    @Column(nullable = false)
    String dni;
    @Email
    @NotNull
    @Column(nullable = false)
    String email;
    Instant dateTimeClientRegister;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ClientState currentState;
    @OneToMany(mappedBy = "owner")
    private List<Wallet> wallets;
}
