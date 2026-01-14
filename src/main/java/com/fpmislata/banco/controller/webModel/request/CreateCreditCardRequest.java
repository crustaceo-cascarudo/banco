package com.fpmislata.banco.controller.webModel.request;

public record CreateCreditCardRequest(
        String iban,
        String fullName
) {
}
