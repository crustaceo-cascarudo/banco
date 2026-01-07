package com.fpmislata.banco.controller.webModel.request;

public record LoginUserRequest(
    String name,
    String plainPassword
) {

}
