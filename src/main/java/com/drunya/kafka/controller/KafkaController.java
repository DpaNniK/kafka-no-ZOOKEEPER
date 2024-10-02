package com.drunya.kafka.controller;

import com.drunya.kafka.enumiration.kafka.Command;
import com.drunya.kafka.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.drunya.kafka.util.KafkaUtils.T_ONE_TOPIC_NAME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka/t-one-group-school")
@Slf4j
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody Command command) {
        log.info(String.format("send-message: %s", command));
        kafkaProducer.sendMessage(T_ONE_TOPIC_NAME, command.getCommand());
    }
}
