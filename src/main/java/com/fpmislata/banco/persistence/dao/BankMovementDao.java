package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.persistence.dao.impl.entity.BankMovementJpaEntity;

import java.util.List;
import java.util.Optional;

public interface BankMovementDao extends GenericDao<BankMovementJpaEntity> {
    Optional<BankMovementJpaEntity> findById(Long id);
    List<BankMovementJpaEntity> findOriginAccountIban(String iban);
    List<BankMovementJpaEntity> findByRecipientAccountIban(String iban);
    List<BankMovementJpaEntity> findByOriginCreditCardNumber(Long cardNumber);
}
