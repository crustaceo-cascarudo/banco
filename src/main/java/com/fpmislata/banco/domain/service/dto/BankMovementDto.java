package com.fpmislata.banco.domain.service.dto;

import com.fpmislata.banco.domain.enums.MovementType;
import com.fpmislata.banco.domain.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record BankMovementDto(
        Long id,
        @NotNull
        MovementType movementType,
        @NotNull
        PaymentMethod paymentMethod,
        @NotNull
        String originAccountIban,
        Long originCreditCardNumber,
        @NotNull
        String recipientAccountIban,
        Date movementDate,
        @NotNull
        float amount,
        String concept
) {
}
