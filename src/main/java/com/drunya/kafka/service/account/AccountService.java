package com.drunya.kafka.service.account;

import com.drunya.kafka.dto.AccountDto;
import com.drunya.kafka.model.Account;

import java.util.UUID;

public interface AccountService {

    AccountDto saveAccount(AccountDto accountDto);

    Account findAccountById(UUID accountId);
}
