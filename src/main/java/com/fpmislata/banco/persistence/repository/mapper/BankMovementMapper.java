package com.fpmislata.banco.persistence.repository.mapper;

import com.fpmislata.banco.domain.enums.MovementType;
import com.fpmislata.banco.domain.enums.PaymentMethod;
import com.fpmislata.banco.domain.repository.entity.BankMovementEntity;
import com.fpmislata.banco.persistence.dao.impl.entity.BankMovementJpaEntity;

public class BankMovementMapper {
    private static BankMovementMapper instance;

    private BankMovementMapper() {
    }

    public static BankMovementMapper getInstance() {
        if (instance == null) {
            instance = new BankMovementMapper();
        }
        return instance;
    }

    public BankMovementJpaEntity fromBankMovementEntityToBankMovementJpaEntity(BankMovementEntity bankMovementEntity) {
        if (bankMovementEntity == null) {
            return null;
        }
        return new BankMovementJpaEntity(
                bankMovementEntity.id(),
                bankMovementEntity.movementType(),
                bankMovementEntity.paymentMethod(),
                bankMovementEntity.originAccountIban(),
                bankMovementEntity.originCreditCardNumber(),
                bankMovementEntity.recipientAccountIban(),
                bankMovementEntity.movementDate(),
                bankMovementEntity.amount(),
                bankMovementEntity.concept()
        );
    }

    public BankMovementEntity fromBankMovementJpaEntityToBankMovementEntity(BankMovementJpaEntity bankMovementJpaEntity) {
        if (bankMovementJpaEntity == null) {
            return null;
        }
        return new BankMovementEntity(
                bankMovementJpaEntity.getId(),
                bankMovementJpaEntity.getMovementType(),
                bankMovementJpaEntity.getPaymentMethod(),
                bankMovementJpaEntity.getOriginAccountIban(),
                bankMovementJpaEntity.getOriginCreditCardNumber(),
                bankMovementJpaEntity.getRecipientAccountIban(),
                bankMovementJpaEntity.getMovementDate(),
                bankMovementJpaEntity.getAmount(),
                bankMovementJpaEntity.getConcept()
        );
    }
}
