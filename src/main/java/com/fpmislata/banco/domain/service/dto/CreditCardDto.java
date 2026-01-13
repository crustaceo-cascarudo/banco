package com.fpmislata.banco.domain.service.dto;

import java.util.Date;

public record CreditCardDto(
        Long cardNumber,
        Date expirationDate,
        int cvc,
        String fullName,
        String accountIban
) {
}
