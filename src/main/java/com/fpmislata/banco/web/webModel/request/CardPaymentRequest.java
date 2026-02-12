package com.fpmislata.banco.web.webModel.request;

import com.fpmislata.banco.domain.service.dto.CreditCardDto;
import com.fpmislata.banco.domain.service.dto.UserDto;

public record CardPaymentRequest(
    UserDto user,
    String apiToken,
    CreditCardDto originCreditCard,
    String recipientIban,
    float amount,
    String concept) {
}
