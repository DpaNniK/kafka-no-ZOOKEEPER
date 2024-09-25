package com.drunya.kafka.controller;

import com.drunya.kafka.enumiration.Command;
import com.drunya.kafka.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka/t-one-group-school")
@Slf4j
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Command command) {
        log.info(String.format("send-message: %s", command));
        kafkaProducer.sendMessage(command.getCommand());
    }
}
