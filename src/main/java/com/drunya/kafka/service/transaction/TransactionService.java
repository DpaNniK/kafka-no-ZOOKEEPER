package com.drunya.kafka.service.transaction;

import com.drunya.kafka.dto.TransactionDto;

public interface TransactionService {

    TransactionDto saveTransaction(TransactionDto transactionDto);
}
