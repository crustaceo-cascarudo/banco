package com.fpmislata.banco.domain.repository.entity;

import java.util.Date;

public record CreditCardEntity(
        Long cardNumber,
        Date expirationDate,
        int cvc,
        String fullName,
        String accountIban
) {
}
