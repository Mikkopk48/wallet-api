package com.mikko.wallet_api.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Wallet {
    UUID id;
    UUID ownerId;
    String ownerName;
    Currency currency;
    BigDecimal balance;
    Instant dateTimeWalletRegister;
    WalletState state;
}
