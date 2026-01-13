package com.fpmislata.banco.domain.repository;


import com.fpmislata.banco.domain.repository.entity.BankMovementEntity;

import java.util.List;
import java.util.Optional;

public interface BankMovementRepository {
    BankMovementEntity save(BankMovementEntity productEntity);
    Optional<BankMovementEntity> findById(Long id);
    List<BankMovementEntity> findByOriginAccountIban(String iban);
    List<BankMovementEntity> findByRecipientAccountIban(String iban);
    List<BankMovementEntity> findByOriginCreditCardNumber(Long cardNumber);
    void deleteById(Long id);
}
