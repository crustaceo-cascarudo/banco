package com.fpmislata.banco.domain.repository.entity;

public record UserEntity(
    Long id,
    String name,
    String surname1,
    String surname2,
    String dni,
    String passwordHash
) {

}
