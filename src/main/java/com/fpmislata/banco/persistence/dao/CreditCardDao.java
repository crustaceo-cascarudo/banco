package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.persistence.dao.impl.entity.CreditCardJpaEntity;

import java.util.List;
import java.util.Optional;

public interface CreditCardDao extends GenericDao<CreditCardJpaEntity> {
    Optional<CreditCardJpaEntity> findByCardNumber(Long cardNumber);
    List<CreditCardJpaEntity> findByAccountIban(String accountIban);
}
