package com.drunya.kafka.service.client.impl;

import com.drunya.kafka.dto.error.RestErrorDto;
import com.drunya.kafka.exception.RestException;
import com.drunya.kafka.model.Client;
import com.drunya.kafka.repository.ClientRepository;
import com.drunya.kafka.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public Client findClientById(UUID clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RestException(RestErrorDto.builder()
                        .message(String.format("Client with id = %s not found", clientId))
                        .status(HttpStatus.NOT_FOUND)
                        .build()));
    }
}
