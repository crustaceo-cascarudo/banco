package com.fpmislata.banco.web.webModel.response;

public record LoginResponse(
    String token,
    UserResponse user) {

}
