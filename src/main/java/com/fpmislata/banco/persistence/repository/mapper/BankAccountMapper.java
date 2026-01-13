package com.fpmislata.banco.persistence.repository.mapper;

import com.fpmislata.banco.domain.repository.entity.BankAccountEntity;
import com.fpmislata.banco.persistence.dao.impl.entity.BankAccountJpaEntity;

import java.util.ArrayList;

public class BankAccountMapper {
    private static BankAccountMapper instance;

    private BankAccountMapper() {
    }

    public static BankAccountMapper getInstance() {
        if (instance == null) {
            instance = new BankAccountMapper();
        }
        return instance;
    }

    public BankAccountJpaEntity fromBankAccountEntityToBankAccountJpaEntity(BankAccountEntity bankAccountEntity) {
        if (bankAccountEntity == null) {
            return null;
        }
        return new BankAccountJpaEntity(
                bankAccountEntity.iban(),
                bankAccountEntity.balance(),
                bankAccountEntity.userId()
        );
    }

    public BankAccountEntity fromBankAccountJpaEntityToBankAccountEntity(BankAccountJpaEntity bankAccountJpaEntity) {
        if (bankAccountJpaEntity == null) {
            return null;
        }
        return new BankAccountEntity(
                bankAccountJpaEntity.getIban(),
                bankAccountJpaEntity.getBalance(),
                bankAccountJpaEntity.getUserId()
        );
    }
}
