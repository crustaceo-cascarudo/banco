package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.persistence.dao.impl.entity.BankAccountJpaEntity;

import java.util.List;
import java.util.Optional;

public interface BankAccountDao extends GenericDao<BankAccountJpaEntity> {
    Optional<BankAccountJpaEntity> findByIban(String iban);
    List<BankAccountJpaEntity> findByUserId(Long userId);
    void delete(String iban);
}
