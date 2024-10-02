package com.drunya.kafka.mapper;

import com.drunya.kafka.dto.TransactionDto;
import com.drunya.kafka.model.Account;
import com.drunya.kafka.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", source = "account")
    Transaction toEntity(TransactionDto transactionDto, Account account);

    @Mapping(target = "accountId", source = "transaction.account.id")
    TransactionDto toDto(Transaction transaction);
}
