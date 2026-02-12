package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.web.webModel.request.CreateCreditCardRequest;
import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.domain.mapper.CreditCardMapper;
import com.fpmislata.banco.domain.repository.CreditCardRepository;
import com.fpmislata.banco.domain.repository.entity.CreditCardEntity;
import com.fpmislata.banco.domain.service.CreditCardService;
import com.fpmislata.banco.domain.service.dto.CreditCardDto;
import jakarta.transaction.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CreditCardServiceImpl implements CreditCardService {

  private final CreditCardRepository creditCardRepository;

  public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
    this.creditCardRepository = creditCardRepository;
  }

  @Override
  @Transactional
  public CreditCardDto create(CreateCreditCardRequest request) {
    CreditCardEntity cardEntity = new CreditCardEntity(
        generateCardNumber(),
        Date.valueOf(LocalDate.now().plusYears(3)),
        generateCvc(),
        request.fullName(),
        request.fullName());

    if (findByCardNumber(cardEntity.cardNumber()).isPresent()) {
      create(request);
    }

    return CreditCardMapper.getInstance().fromCreditCardToCreditCardDto(
        CreditCardMapper.getInstance().fromCreditCardEntityToCreditCard(
            creditCardRepository.save(cardEntity)));
  }

  private Long generateCardNumber() {
    int industryIdentifier = 4;
    int issuingBankNumber = 75643;

    StringBuilder individualAccount = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      int r = (int) (Math.random() * (10));
      individualAccount.append(r);
    }

    String cardNumber = String.valueOf(industryIdentifier) + String.valueOf(issuingBankNumber)
        + individualAccount.toString();
    return Long.parseLong(cardNumber);
  }

  private int generateCvc() {
    return (int) (Math.random() * (999 + 1)) + 100;
  }

  @Override
  @Transactional
  public CreditCardDto update(CreditCardDto cardDto) {
    creditCardRepository.findByCardNumber(cardDto.cardNumber())
        .orElseThrow(() -> new ResourceNotFoundException("No card found with number " + cardDto.cardNumber()));

    CreditCardEntity cardEntity = CreditCardMapper.getInstance().fromCreditCardToCreditCardEntity(
        CreditCardMapper.getInstance().fromCreditCardDtoToCreditCard(cardDto));

    return CreditCardMapper.getInstance().fromCreditCardToCreditCardDto(
        CreditCardMapper.getInstance().fromCreditCardEntityToCreditCard(
            creditCardRepository.update(cardEntity)));
  }

  @Override
  public Optional<CreditCardDto> findByCardNumber(Long cardNumber) {
    return creditCardRepository
        .findByCardNumber(cardNumber)
        .map(CreditCardMapper.getInstance()::fromCreditCardEntityToCreditCard)
        .map(CreditCardMapper.getInstance()::fromCreditCardToCreditCardDto);
  }

  @Override
  public CreditCardDto getByCardNumber(Long cardNumber) {
    return creditCardRepository
        .findByCardNumber(cardNumber)
        .map(CreditCardMapper.getInstance()::fromCreditCardEntityToCreditCard)
        .map(CreditCardMapper.getInstance()::fromCreditCardToCreditCardDto)
        .orElseThrow(() -> new ResourceNotFoundException("Card with card number " + cardNumber + " does not exist"));
  }

  @Override
  public List<CreditCardDto> findByAccountIban(String iban) {
    return creditCardRepository
        .findByAccountIban(iban)
        .stream()
        .map(CreditCardMapper.getInstance()::fromCreditCardEntityToCreditCard)
        .map(CreditCardMapper.getInstance()::fromCreditCardToCreditCardDto)
        .toList();
  }

  @Override
  @Transactional
  public void deleteByCardNumber(Long cardNumber) {
    if (findByCardNumber(cardNumber).isEmpty()) {
      throw new ResourceNotFoundException("No card found with card number " + cardNumber);
    }
    creditCardRepository.deleteByCardNumber(cardNumber);
  }
}
