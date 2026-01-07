package com.fpmislata.banco.domain.repository.entity;

import com.fpmislata.banco.domain.enumerado.Role;

public record UserEntity(
    Long id,
    String name,
    String passwordHash,
    Role role
) {

}
