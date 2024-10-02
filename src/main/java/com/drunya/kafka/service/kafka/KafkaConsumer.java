package com.drunya.kafka.service.kafka;

import com.drunya.kafka.dto.AccountDto;
import com.drunya.kafka.dto.TransactionDto;
import com.drunya.kafka.service.account.AccountService;
import com.drunya.kafka.service.transaction.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    private final AccountService accountService;

    private final TransactionService transactionService;

    public KafkaConsumer(ObjectMapper objectMapper,
                         @Qualifier("accountServiceImpl") AccountService accountService,
                         @Qualifier("transactionServiceImpl") TransactionService transactionService) {
        this.objectMapper = objectMapper;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.t-one}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenMessageTOneTopic(ConsumerRecord<String, Object> record) {
        System.out.println(record.value());
    }

    @KafkaListener(topics = "${spring.kafka.topic.account}", groupId = "${spring.kafka.consumer.group-id}")
    @SneakyThrows
    public void listenMessageAccount(ConsumerRecord<String, Object> record) {
        log.info("Получение сообщение в топик при создании аккаунта {}", record.topic());
        AccountDto accountDto = accountService.saveAccount(objectMapper.readValue(record.value().toString(), AccountDto.class));
        log.info("Добавлен аккаунт {}", accountDto);
    }

    @KafkaListener(topics = "${spring.kafka.topic.transaction}", groupId = "${spring.kafka.consumer.group-id}")
    @SneakyThrows
    public void listenMessageTransaction(ConsumerRecord<String, Object> record) {
        log.info("Получение сообщение в топик при сохранении транзакции {}", record.topic());
        TransactionDto transactionDto = transactionService.saveTransaction(objectMapper.readValue(record.value().toString(), TransactionDto.class));
        System.out.println(record.value());
        log.info("Транзакция сохранена {}", transactionDto);
    }

    @KafkaListener(topics = "${spring.kafka.topic.trace}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenMessageTrace(ConsumerRecord<String, Object> record) {
        System.out.println(record.value());
    }

    @KafkaListener(topics = "${spring.kafka.topic.error-trace}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenMessageErrorTrace(ConsumerRecord<String, Object> record) {
        System.out.println(record.value());
    }
}
