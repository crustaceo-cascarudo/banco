package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.service.dto.BankAccountDto;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {
    BankAccountDto create(Long userId);
    BankAccountDto update(BankAccountDto bankAccountDto);
    Optional<BankAccountDto> findByIban(String iban);
    List<BankAccountDto> findByUserId(Long userId);
    void delete(String iban);
}
