package com.drunya.kafka.controller;

import com.drunya.kafka.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka/t-one-group-school")
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
    }
}
