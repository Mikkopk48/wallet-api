package com.mikko.wallet_api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "movements")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Instant moment;

    @Column(nullable = false)
    private String description;

    @Column(name = "operation_id", nullable = false)
    private UUID operationId;

    @Column(name = "wallet_resultant_balance", nullable = false)
    private BigDecimal walletResultantBalance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;
}