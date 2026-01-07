package com.fpmislata.banco.controller.webModel.response;


import com.fpmislata.banco.domain.enumerado.Role;

public record UserResponse(
    Long id,
    String name,
    Role role
) {

}
