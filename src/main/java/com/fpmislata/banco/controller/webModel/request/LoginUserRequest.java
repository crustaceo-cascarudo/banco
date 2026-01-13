package com.fpmislata.banco.controller.webModel.request;

public record LoginUserRequest(
    String dni,
    String plainPassword) {

}
