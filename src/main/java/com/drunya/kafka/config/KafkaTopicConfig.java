package com.drunya.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.drunya.kafka.util.KafkaUtils.*;

@Configuration
public class KafkaTopicConfig {

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

        return new NewTopic(T_ONE_TOPIC_NAME, 2, (short) 1);
    }

    @Bean
    public NewTopic accountTopic() {
        return new NewTopic(ACCOUNT_TOPIC_NAME, 2, (short) 1);
    }

    @Bean
    public NewTopic transactionTopic() {
        return new NewTopic(TRANSACTION_TOPIC_NAME, 2, (short) 1);
    }

    @Bean
    public NewTopic traceTopic() {
        return new NewTopic(TRACE_TOPIC_NAME, 2, (short) 1);
    }

    @Bean
    public NewTopic errorTraceTopic() {
        return new NewTopic(ERROR_TRACE_TOPIC_NAME, 2, (short) 1);
    }
}
