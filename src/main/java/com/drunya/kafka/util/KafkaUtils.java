package com.drunya.kafka.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaUtils {

    public static String T_ONE_TOPIC_NAME;
    public static String ACCOUNT_TOPIC_NAME;
    public static String TRANSACTION_TOPIC_NAME;
    public static String TRACE_TOPIC_NAME;
    public static String ERROR_TRACE_TOPIC_NAME;

    @Value("${spring.kafka.topic.t-one}")
    private String tOneTopicNameProp;

    @Value("${spring.kafka.topic.account}")
    private String accountTopicNameProp;

    @Value("${spring.kafka.topic.transaction}")
    private String transactionTopicNameProp;

    @Value("${spring.kafka.topic.trace}")
    private String traceTopicNameProp;

    @Value("${spring.kafka.topic.error-trace}")
    private String errorTraceTopicNameProp;

    @PostConstruct
    void init() {
        T_ONE_TOPIC_NAME = tOneTopicNameProp;
        ACCOUNT_TOPIC_NAME = accountTopicNameProp;
        TRANSACTION_TOPIC_NAME = transactionTopicNameProp;
        TRACE_TOPIC_NAME = traceTopicNameProp;
        ERROR_TRACE_TOPIC_NAME = errorTraceTopicNameProp;
    }
}

