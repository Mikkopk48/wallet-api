package com.mikko.wallet_api.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Movement {
    UUID id;
    UUID walletId;
    BigDecimal amount;
    Instant moment;
    String description;
    UUID oprationId;
    BigDecimal walletResultantBalance;
    MovementType type;
}
