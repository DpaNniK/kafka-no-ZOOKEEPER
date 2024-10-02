package com.drunya.kafka.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TraceDto {

    @Schema(description = "Название метода")
    private String methodName;

    @Schema(description = "Продолжительность работы метода")
    private long duration;

    @Schema(description = "Аргументы метода")
    private Object[] args;
}
