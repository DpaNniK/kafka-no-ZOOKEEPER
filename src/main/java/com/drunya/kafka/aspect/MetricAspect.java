package com.drunya.kafka.aspect;

import com.drunya.kafka.dto.TraceDto;
import com.drunya.kafka.properties.MetricProperties;
import com.drunya.kafka.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import static com.drunya.kafka.util.KafkaUtils.TRACE_TOPIC_NAME;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MetricAspect {

    private final StopWatch stopWatch = new StopWatch();

    private final MetricProperties metricProperties;

    private final KafkaProducer kafkaProducer;

    @Around("@annotation(com.drunya.kafka.annotation.Metric)")
    @SneakyThrows
    public Object measureExecutionTime(ProceedingJoinPoint proceedingJoinPoint) {
        long limitMs = metricProperties.getLimitMs();
        String methodName = proceedingJoinPoint.getSignature().getName();
        stopWatch.start();
        Object proceed = proceedingJoinPoint.proceed();
        stopWatch.stop();
        long duration = stopWatch.getTotalTimeMillis();
        if (limitMs < duration) {
            log.warn("Метод {} работал дольше лимита = {}, трей отправлен в топик - {}", methodName, limitMs, TRACE_TOPIC_NAME);
            TraceDto traceDto = TraceDto.builder()
                    .methodName(methodName)
                    .duration(duration)
                    .args(proceedingJoinPoint.getArgs())
                    .build();
            kafkaProducer.sendMessage(TRACE_TOPIC_NAME, traceDto);
        }
        log.info("Время работы метода {} = {}", methodName, duration);
        return proceed;
    }
}
