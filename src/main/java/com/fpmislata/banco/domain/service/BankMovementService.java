package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.service.dto.BankMovementDto;

import java.util.List;
import java.util.Optional;

public interface BankMovementService {
    BankMovementDto create(BankMovementDto bankMovementDto);
    BankMovementDto update(BankMovementDto bankMovementDto);
    Optional<BankMovementDto> findById(Long id);
    List<BankMovementDto> findByOriginAccountIban(String iban);
    List<BankMovementDto> findByRecipientAccountIban(String iban);
    List<BankMovementDto> findByOriginCreditCardNumber(Long cardNumber);
    void delete(Long id);
}
