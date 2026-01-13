package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.service.dto.BankAccountDto;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {
    BankAccountDto create(BankAccountDto bankAccountDto);
    BankAccountDto update(BankAccountDto bankAccountDto);
    Optional<BankAccountDto> findByIban(String iban);
    List<BankAccountDto> findByUserId(Long userId);
    void delete(String iban);
}
