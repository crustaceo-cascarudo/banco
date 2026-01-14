package com.fpmislata.banco.domain.service.dto;

import jakarta.validation.constraints.NotNull;

public record UserDto(
        Long id,
        @NotNull(message = "Name cannot be null") 
        String name,
        @NotNull (message = "Surname 1 cannot be null")
        String surname1,
        String surname2,
        @NotNull(message = "DNI cannot be null")
        String dni,
        @NotNull(message = "Password cannot be null") 
        String plainPassword,
        String passwordHash
    ) {

}
