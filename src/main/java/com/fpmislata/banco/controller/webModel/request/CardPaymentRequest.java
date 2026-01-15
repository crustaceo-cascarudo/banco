package com.fpmislata.banco.controller.webModel.request;

import com.fpmislata.banco.domain.service.dto.CreditCardDto;
import com.fpmislata.banco.domain.service.dto.UserDto;

import java.util.Date;

public record CardPaymentRequest(
        UserDto user,
        String apiToken,
        CreditCardDto originCreditCard,
        String recipientIban,
        float amount,
        String concept
) {
}
