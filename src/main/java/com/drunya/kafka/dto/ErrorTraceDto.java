package com.drunya.kafka.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorTraceDto {

    @Schema(description = "Информация по трассировке метода")
    private TraceDto traceDto;

    @Schema(description = "Стек-трей")
    private StackTraceElement[] stackTrace;

    @Schema(description = "Сообщение об ошибке")
    private String message;
}
