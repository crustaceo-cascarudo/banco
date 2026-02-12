package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.web.webModel.request.CreateCreditCardRequest;
import com.fpmislata.banco.domain.service.dto.CreditCardDto;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {
  CreditCardDto create(CreateCreditCardRequest request);

  CreditCardDto update(CreditCardDto cardDto);

  Optional<CreditCardDto> findByCardNumber(Long cardNumber);

  CreditCardDto getByCardNumber(Long cardNumber);

  List<CreditCardDto> findByAccountIban(String iban);

  void deleteByCardNumber(Long cardNumber);
}
