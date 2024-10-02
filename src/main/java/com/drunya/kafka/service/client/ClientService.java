package com.drunya.kafka.service.client;

import com.drunya.kafka.model.Client;

import java.util.UUID;

public interface ClientService {

    Client findClientById(UUID clientId);
}
