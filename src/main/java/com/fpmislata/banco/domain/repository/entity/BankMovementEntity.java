package com.fpmislata.banco.domain.repository.entity;

import com.fpmislata.banco.domain.enums.MovementType;
import com.fpmislata.banco.domain.enums.PaymentMethod;

import java.util.Date;

public record BankMovementEntity(
        Long id,
        MovementType movementType,
        PaymentMethod paymentMethod,
        String originAccountIban,
        Long originCreditCardNumber,
        String recipientAccountIban,
        Date movementDate,
        float amount,
        String concept
) {
}
