package com.drunya.kafka.controller;

import com.drunya.kafka.dto.AccountDto;
import com.drunya.kafka.enumiration.entity.AccountType;
import com.drunya.kafka.service.kafka.KafkaProducer;
import com.drunya.kafka.util.KafkaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Slf4j
public class AccountController {

    private final KafkaProducer kafkaProducer;

    @PostMapping
    public void createAccount(@RequestParam UUID clientId,
                              @RequestParam AccountType accountType) {
        AccountDto accountDto = AccountDto.builder()
                .clientId(clientId)
                .accountType(accountType)
                .balance(BigDecimal.ZERO)
                .build();
        kafkaProducer.sendMessage(KafkaUtils.ACCOUNT_TOPIC_NAME, accountDto);
    }
}
