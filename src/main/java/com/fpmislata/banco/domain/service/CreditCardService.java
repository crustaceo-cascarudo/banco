package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.service.dto.CreditCardDto;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {
    CreditCardDto create(CreditCardDto cardDto);
    CreditCardDto update(CreditCardDto cardDto);
    Optional<CreditCardDto> findByCardNumber(Long cardNumber);
    CreditCardDto getByCardNumber(Long cardNumber);
    List<CreditCardDto> findByAccountIban(String iban);
    void deleteByCardNumber(Long cardNumber);
}
