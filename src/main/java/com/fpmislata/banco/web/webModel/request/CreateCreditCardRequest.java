package com.fpmislata.banco.web.webModel.request;

public record CreateCreditCardRequest(
    String iban,
    String fullName) {
}
