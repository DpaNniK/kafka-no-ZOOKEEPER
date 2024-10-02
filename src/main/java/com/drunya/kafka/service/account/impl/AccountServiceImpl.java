package com.drunya.kafka.service.account.impl;

import com.drunya.kafka.annotation.Metric;
import com.drunya.kafka.dto.AccountDto;
import com.drunya.kafka.dto.error.RestErrorDto;
import com.drunya.kafka.exception.RestException;
import com.drunya.kafka.mapper.AccountMapper;
import com.drunya.kafka.model.Account;
import com.drunya.kafka.model.Client;
import com.drunya.kafka.repository.AccountRepository;
import com.drunya.kafka.service.account.AccountService;
import com.drunya.kafka.service.client.ClientService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final ClientService clientService;

    public AccountServiceImpl(AccountRepository accountRepository,
                              AccountMapper accountMapper,
                              @Qualifier("clientServiceImpl") ClientService clientService) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.clientService = clientService;
    }

    @Transactional
    @Metric
    @SneakyThrows
    public AccountDto saveAccount(AccountDto accountDto) {
        Thread.sleep(2000);
        UUID clientId = accountDto.getClientId();
        if (checkIfExistAccountByClientId(clientId)) {
            throw new RestException(RestErrorDto.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(String.format("Client with id %s already exists", clientId))
                    .build());
        }
        Client client = clientService.findClientById(clientId);
        Account newAccount = accountMapper.toEntity(accountDto, client);
        Account saveAccount = accountRepository.save(newAccount);
        log.info("Saved account with id = {}", saveAccount.getId());
        return accountMapper.toDto(saveAccount);
    }

    private boolean checkIfExistAccountByClientId(UUID clientId) {
        return accountRepository.existsByClientId(clientId);
    }

    public Account findAccountById(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RestException(RestErrorDto.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message(String.format("Аккаунт с id = %s не найден", accountId))
                        .build()));
    }
}
