package com.fpmislata.banco.persistence.repository.mapper;

import com.fpmislata.banco.domain.repository.entity.CreditCardEntity;
import com.fpmislata.banco.persistence.dao.impl.entity.CreditCardJpaEntity;

public class CreditCardMapper {
    private static CreditCardMapper instance;

    private CreditCardMapper() {
    }

    public static CreditCardMapper getInstance() {
        if (instance == null) {
            instance = new CreditCardMapper();
        }
        return instance;
    }

    public CreditCardJpaEntity fromCreditCardEntityToCreditCardJpaEntity(CreditCardEntity creditCardEntity) {
        if (creditCardEntity == null) {
            return null;
        }
        return new CreditCardJpaEntity(
                creditCardEntity.cardNumber(),
                creditCardEntity.expirationDate(),
                creditCardEntity.cvc(),
                creditCardEntity.fullName(),
                creditCardEntity.accountIban()
        );
    }

    public CreditCardEntity fromCreditCardJpaEntityToCreditCardEntity(CreditCardJpaEntity creditCardJpaEntity) {
        if (creditCardJpaEntity == null) {
            return null;
        }
        return new CreditCardEntity(
                creditCardJpaEntity.getCardNumber(),
                creditCardJpaEntity.getExpirationDate(),
                creditCardJpaEntity.getCvc(),
                creditCardJpaEntity.getFullName(),
                creditCardJpaEntity.getAccountIban()
        );
    }

}
