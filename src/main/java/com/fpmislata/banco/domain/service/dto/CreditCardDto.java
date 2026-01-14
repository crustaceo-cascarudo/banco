package com.fpmislata.banco.domain.service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreditCardDto(
        @NotNull
        Long cardNumber,
        @NotNull
        Date expirationDate,
        @NotNull
        int cvc,
        @NotNull
        String fullName,
        @NotNull
        String accountIban
) {
}
