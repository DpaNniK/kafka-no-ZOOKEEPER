package com.drunya.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "metric.execution.time")
public class MetricProperties {

    private long limitMs;

    private long limitSec;
}
