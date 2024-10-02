package com.drunya.kafka.dto;

import com.drunya.kafka.enumiration.entity.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AccountDto {

    @Schema(description = "Информация о владельце аккаунта")
    private UUID clientId;

    @Schema(description = "Тип аккаунта")
    private AccountType accountType;

    @Schema(description = "Баланс аккаунта")
    private BigDecimal balance;
}
