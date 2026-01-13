package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.repository.entity.BankAccountEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository {
    BankAccountEntity save(BankAccountEntity bankAccountEntity);
    void delete(String iban);
    Optional<BankAccountEntity> findByIban(String iban);
    List<BankAccountEntity> findByUserId(Long userId);
}
