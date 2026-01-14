package com.fpmislata.banco.domain.service.dto;

import jakarta.validation.constraints.NotNull;

public record BankAccountDto(
        @NotNull
        String iban,
        @NotNull
        Double balance,
        @NotNull
        Long userId
){

}
