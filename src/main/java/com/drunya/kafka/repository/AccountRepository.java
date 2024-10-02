package com.drunya.kafka.repository;

import com.drunya.kafka.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    boolean existsByClientId(UUID clientId);
}
