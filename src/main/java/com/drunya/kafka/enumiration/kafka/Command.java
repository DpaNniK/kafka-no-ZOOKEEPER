package com.drunya.kafka.enumiration.kafka;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Schema(description = "enum-команды", enumAsRef = true)
public enum Command {

    START("Стартуем"),

    PAUSE("Приостановимся"),

    STOP("Остановимся");

    private final String command;
}
