package com.drunya.kafka.service.transaction.impl;

import com.drunya.kafka.dto.TransactionDto;
import com.drunya.kafka.mapper.TransactionMapper;
import com.drunya.kafka.model.Account;
import com.drunya.kafka.model.Transaction;
import com.drunya.kafka.repository.TransactionRepository;
import com.drunya.kafka.service.account.AccountService;
import com.drunya.kafka.service.transaction.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final AccountService accountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  TransactionMapper transactionMapper,
                                  @Qualifier("accountServiceImpl") AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountService = accountService;
    }

    @Transactional
    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        Account account = accountService.findAccountById(transactionDto.getAccountId());
        Transaction newTransaction = transactionMapper.toEntity(transactionDto, account);
        Transaction saveTransactional = transactionRepository.save(newTransaction);
        return transactionMapper.toDto(saveTransactional);
    }
}
