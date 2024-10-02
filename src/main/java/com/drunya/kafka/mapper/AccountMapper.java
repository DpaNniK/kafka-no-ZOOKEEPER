package com.drunya.kafka.mapper;

import com.drunya.kafka.dto.AccountDto;
import com.drunya.kafka.model.Account;
import com.drunya.kafka.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", source = "client")
    Account toEntity(AccountDto accountDto, Client client);

    @Mapping(target = "clientId", source = "account.client.id")
    AccountDto toDto(Account account);
}
