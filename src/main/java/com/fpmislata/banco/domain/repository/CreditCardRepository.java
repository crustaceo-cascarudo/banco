package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.repository.entity.CreditCardEntity;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository {
    CreditCardEntity update(CreditCardEntity cardEntity);
    CreditCardEntity save(CreditCardEntity cardEntity);
    Optional<CreditCardEntity> findByCardNumber(Long cardNumber);
    List<CreditCardEntity> findByAccountIban(String iban);
    void deleteByCardNumber(Long cardNumber);
}
