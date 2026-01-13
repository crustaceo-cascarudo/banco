package com.fpmislata.banco.domain.service.dto;

public record BankAccountDto(
        String iban,
        Double balance,
        Long userId
){

}
