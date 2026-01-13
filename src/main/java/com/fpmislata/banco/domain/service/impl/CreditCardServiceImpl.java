package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.Exception.BusinessException;
import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.domain.mapper.CreditCardMapper;
import com.fpmislata.banco.domain.repository.CreditCardRepository;
import com.fpmislata.banco.domain.repository.entity.CreditCardEntity;
import com.fpmislata.banco.domain.service.CreditCardService;
import com.fpmislata.banco.domain.service.dto.CreditCardDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    @Transactional
    public CreditCardDto create(CreditCardDto cardDto) {
        if(findByCardNumber(cardDto.cardNumber()).isPresent()){
            throw new BusinessException("Card with id '"+cardDto.cardNumber()+"' already exists");
        }

        CreditCardEntity ingredientEntity = CreditCardMapper.getInstance().fromCreditCardToCreditCardEntity(
                CreditCardMapper.getInstance().fromCreditCardDtoToCreditCard(cardDto)
        );

        return CreditCardMapper.getInstance().fromCreditCardToCreditCardDto(
                CreditCardMapper.getInstance().fromCreditCardEntityToCreditCard(
                        creditCardRepository.save(ingredientEntity)
                )
        );
    }

    @Override
    @Transactional
    public CreditCardDto update(CreditCardDto cardDto) {
        creditCardRepository.findByCardNumber(cardDto.cardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("No card found with number "+cardDto.cardNumber()));

        CreditCardEntity cardEntity = CreditCardMapper.getInstance().fromCreditCardToCreditCardEntity(
                CreditCardMapper.getInstance().fromCreditCardDtoToCreditCard(cardDto)
        );

        return CreditCardMapper.getInstance().fromCreditCardToCreditCardDto(
                CreditCardMapper.getInstance().fromCreditCardEntityToCreditCard(
                        creditCardRepository.save(cardEntity)
                )
        );
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
                .orElseThrow(() -> new ResourceNotFoundException("Card with card number "+cardNumber+" does not exist"));
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
    public void deleteByCardNumber(Long cardNumber) {
        if(findByCardNumber(cardNumber).isEmpty()){
            throw new ResourceNotFoundException("No card found with card number "+cardNumber);
        }
        creditCardRepository.deleteByCardNumber(cardNumber);
    }
}
