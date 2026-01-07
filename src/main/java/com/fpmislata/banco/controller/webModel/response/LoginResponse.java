package com.fpmislata.banco.controller.webModel.response;

public record LoginResponse(
    String token,
    UserResponse user
) {

}
