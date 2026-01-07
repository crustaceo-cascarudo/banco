package com.fpmislata.banco.domain.service.dto;

import com.fpmislata.banco.domain.enumerado.Role;
import jakarta.validation.constraints.NotNull;

public record UserDto(
        Long id,
        @NotNull(message = "Name cannot be null") 
        String name,
        @NotNull(message = "Password cannot be null") 
        String plainPassword,
        String passwordHash,
        @NotNull(message = "Role cannot be null") 
        Role role
    ) {

}
