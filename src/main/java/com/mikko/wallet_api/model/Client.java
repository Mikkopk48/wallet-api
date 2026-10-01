package com.mikko.wallet_api.model;

import java.time.Instant;
import java.util.UUID;

public class Client {
    UUID id;
    String fullname;
    int  dni;
    String mail;
    Instant dateTimeClientRegister;
    ClientState currentState;
}
