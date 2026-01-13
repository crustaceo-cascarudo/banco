package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.enums.MovementType;
import com.fpmislata.banco.domain.enums.PaymentMethod;
import com.fpmislata.banco.domain.model.BankMovement;
import com.fpmislata.banco.domain.repository.entity.BankMovementEntity;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;

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

    public BankMovementDto fromBankMovementToBankMovementDto(BankMovement bankMovement) {
        if (bankMovement == null) {
            return null;
        }
        return new BankMovementDto(
                bankMovement.getId(),
                bankMovement.getMovementType(),
                bankMovement.getPaymentMethod(),
                bankMovement.getOriginAccountIban(),
                bankMovement.getOriginCreditCardNumber(),
                bankMovement.getRecipientAccountIban(),
                bankMovement.getMovementDate(),
                bankMovement.getAmount(),
                bankMovement.getConcept()
        );
    }

    public BankMovement fromBankMovementDtoToBankMovement(BankMovementDto bankMovementDto) {
        if (bankMovementDto == null) {
            return null;
        }
        return new BankMovement(
                bankMovementDto.id(),
                bankMovementDto.movementType(),
                bankMovementDto.paymentMethod(),
                bankMovementDto.originAccountIban(),
                bankMovementDto.originCreditCardNumber(),
                bankMovementDto.recipientAccountIban(),
                bankMovementDto.movementDate(),
                bankMovementDto.amount(),
                bankMovementDto.concept()
        );
    }

    public BankMovementEntity fromBankMovementToBankMovementEntity(BankMovement bankMovement) {
        if (bankMovement == null) {
            return null;
        }
        return new BankMovementEntity(
                bankMovement.getId(),
                bankMovement.getMovementType(),
                bankMovement.getPaymentMethod(),
                bankMovement.getOriginAccountIban(),
                bankMovement.getOriginCreditCardNumber(),
                bankMovement.getRecipientAccountIban(),
                bankMovement.getMovementDate(),
                bankMovement.getAmount(),
                bankMovement.getConcept()
        );
    }

    public BankMovement fromBankMovementEntityToBankMovement(BankMovementEntity bankMovementEntity) {
        if (bankMovementEntity == null) {
            return null;
        }
        return new BankMovement(
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
}
