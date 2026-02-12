
package com.fpmislata.banco.domain.repository.entity;

import java.time.LocalDateTime;

import com.fpmislata.banco.domain.enums.ApiClientStatus;

public record ApiClientEntity(
        Long id,
        String clientName,
        String apiKeyHash,
        ApiClientStatus status,
        LocalDateTime createdAt
) {
}
