package com.drunya.kafka.aspect;

import com.drunya.kafka.dto.ErrorTraceDto;
import com.drunya.kafka.dto.TraceDto;
import com.drunya.kafka.service.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.drunya.kafka.util.KafkaUtils.ERROR_TRACE_TOPIC_NAME;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThrowAspect {

    private final KafkaProducer kafkaProducer;

    @Pointcut("execution(* com.drunya.kafka..*(..))")
    public void packagePointcut() {
    }

    @AfterThrowing(pointcut = "packagePointcut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        String message = exception.getMessage();
        log.warn("Throwing exception: {}", message);
        TraceDto traceDto = TraceDto.builder()
                .args(joinPoint.getArgs())
                .methodName(joinPoint.getSignature().getName())
                .build();
        ErrorTraceDto errorTraceDto = ErrorTraceDto.builder()
                .traceDto(traceDto)
                .message(message)
                .stackTrace(exception.getStackTrace())
                .build();
        kafkaProducer.sendMessage(ERROR_TRACE_TOPIC_NAME, errorTraceDto);
    }
}
