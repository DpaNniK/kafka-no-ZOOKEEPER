package com.drunya.kafka.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

    @Schema(description = "Идентификатор транзакции")
    private UUID transactionId;

    @Schema(description = "Аккаунт, с которого совершена транзакция")
    private UUID accountId;
}
