package com.drunya.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.t-one}", groupId = "${spring.kafka.consumer.group-id}")
    public void ListenMessage(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
    }
}
