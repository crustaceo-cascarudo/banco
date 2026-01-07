package com.fpmislata.banco.controller.webModel.request;
import com.fpmislata.banco.domain.enumerado.Role;

public record RegisterUserRequest(
    String name,
    String password,
    Role role
) {

}
