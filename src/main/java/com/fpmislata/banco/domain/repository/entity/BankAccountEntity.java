package com.fpmislata.banco.domain.repository.entity;

public record BankAccountEntity (
        String iban,
        Double balance,
        Long userId
){
}
