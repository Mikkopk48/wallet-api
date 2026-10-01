package com.mikko.wallet_api.model;

public enum ClientState {
    ABLE,
    INACTIVE,
    BLOCKED,
    UNDER_REWIEW,
    CLOSED,
    DELINQUENT,    // Cliente con atrasos en pagos
    IRRECOVERABLE  // Cliente con deuda incobrable
}
