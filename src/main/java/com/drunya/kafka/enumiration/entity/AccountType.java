package com.drunya.kafka.enumiration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {

    DEBIT("Дебетовый счет"),

    CREDIT("Кредитный счет");

    private final String description;
}
