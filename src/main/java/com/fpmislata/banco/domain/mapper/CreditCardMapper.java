package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.CreditCard;
import com.fpmislata.banco.domain.repository.entity.CreditCardEntity;
import com.fpmislata.banco.domain.service.dto.CreditCardDto;

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

    public CreditCardDto fromCreditCardToCreditCardDto(CreditCard creditCard) {
        if (creditCard == null) {
            return null;
        }
        return new CreditCardDto(
                creditCard.getCardNumber(),
                creditCard.getExpirationDate(),
                creditCard.getCvc(),
                creditCard.getFullName(),
                creditCard.getAccountIban()
        );
    }

    public CreditCard fromCreditCardDtoToCreditCard(CreditCardDto creditCardDto) {
        if (creditCardDto == null) {
            return null;
        }
        return new CreditCard(
                creditCardDto.cardNumber(),
                creditCardDto.expirationDate(),
                creditCardDto.cvc(),
                creditCardDto.fullName(),
                creditCardDto.accountIban()
        );
    }

    public CreditCardEntity fromCreditCardToCreditCardEntity(CreditCard creditCard) {
        if (creditCard == null) {
            return null;
        }
        return new CreditCardEntity(
                creditCard.getCardNumber(),
                creditCard.getExpirationDate(),
                creditCard.getCvc(),
                creditCard.getFullName(),
                creditCard.getAccountIban()
        );
    }

    public CreditCard fromCreditCardEntityToCreditCard(CreditCardEntity creditCardEntity) {
        if (creditCardEntity == null) {
            return null;
        }
        return new CreditCard(
                creditCardEntity.cardNumber(),
                creditCardEntity.expirationDate(),
                creditCardEntity.cvc(),
                creditCardEntity.fullName(),
                creditCardEntity.accountIban()
        );
    }
}
