package com.fpmislata.banco.web.webModel.request;

public record LoginUserRequest(
    String dni,
    String plainPassword) {
}
