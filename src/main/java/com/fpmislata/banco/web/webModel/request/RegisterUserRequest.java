package com.fpmislata.banco.web.webModel.request;

public record RegisterUserRequest(
    String name,
    String surname,
    String surname2,
    String dni,
    String password) {

}
