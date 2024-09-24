package com.drunya.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.t-one}")
    private String tOneTopicName;

    // Служит поддержкой конфигураций кафка в application property(автозаполнение и прочее)
    // Можно создать свой класс с необходимым префиксом @ConfigurationProperties(prefix = "custom.kafka")
    @Bean
    @Primary
    public KafkaProperties getKafkaProperties() {
        return new KafkaProperties();
    }

    @Bean
    public NewTopic tOneGroupTopic() {
        // Параметр replicationFactor - определяет, сколько копий каждой партиции будет храниться на разных брокерах.
        return new NewTopic(tOneTopicName, 2, (short) 1);
    }
}
