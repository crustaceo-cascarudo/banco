package com.fpmislata.banco.domain.service.dto;

import com.fpmislata.banco.domain.enums.MovementType;
import com.fpmislata.banco.domain.enums.PaymentMethod;

import java.util.Date;

public record BankMovementDto(
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
