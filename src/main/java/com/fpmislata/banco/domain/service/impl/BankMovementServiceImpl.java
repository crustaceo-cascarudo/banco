package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.Exception.BusinessException;
import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.domain.mapper.BankMovementMapper;
import com.fpmislata.banco.domain.repository.BankMovementRepository;
import com.fpmislata.banco.domain.repository.entity.BankMovementEntity;
import com.fpmislata.banco.domain.service.BankMovementService;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class BankMovementServiceImpl implements BankMovementService {
    private final BankMovementRepository bankMovementRepository;

    public BankMovementServiceImpl(BankMovementRepository bankMovementRepository) {
        this.bankMovementRepository = bankMovementRepository;
    }

    @Override
    @Transactional
    public BankMovementDto create(BankMovementDto bankMovementDto) {
        if(findById(bankMovementDto.id()).isPresent()){
            throw new BusinessException("Bank movement wit id '"+bankMovementDto.id()+"' already exists");
        }

        BankMovementEntity bankMovementEntity = BankMovementMapper.getInstance().fromBankMovementToBankMovementEntity(
                BankMovementMapper.getInstance().fromBankMovementDtoToBankMovement(bankMovementDto)
        );

        return BankMovementMapper.getInstance().fromBankMovementToBankMovementDto(
                BankMovementMapper.getInstance().fromBankMovementEntityToBankMovement(
                        bankMovementRepository.save(bankMovementEntity)
                )
        );
    }

    @Override
    @Transactional
    public BankMovementDto update(BankMovementDto bankMovementDto) {
        bankMovementRepository.findById(bankMovementDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("No bank movement found with iban "+bankMovementDto.id()));

        BankMovementEntity bankAccountEntity = BankMovementMapper.getInstance().fromBankMovementToBankMovementEntity(
                BankMovementMapper.getInstance().fromBankMovementDtoToBankMovement(bankMovementDto)
        );

        return BankMovementMapper.getInstance().fromBankMovementToBankMovementDto(
                BankMovementMapper.getInstance().fromBankMovementEntityToBankMovement(
                        bankMovementRepository.save(bankAccountEntity)
                )
        );
    }

    @Override
    public Optional<BankMovementDto> findById(Long id) {
        return bankMovementRepository
                .findById(id)
                .map(BankMovementMapper.getInstance()::fromBankMovementEntityToBankMovement)
                .map(BankMovementMapper.getInstance()::fromBankMovementToBankMovementDto);
    }

    @Override
    public List<BankMovementDto> findByOriginAccountIban(String iban) {
        return bankMovementRepository
                .findByOriginAccountIban(iban)
                .stream()
                .map(BankMovementMapper.getInstance()::fromBankMovementEntityToBankMovement)
                .map(BankMovementMapper.getInstance()::fromBankMovementToBankMovementDto)
                .toList();
    }

    @Override
    public List<BankMovementDto> findByRecipientAccountIban(String iban) {
        return bankMovementRepository
                .findByRecipientAccountIban(iban)
                .stream()
                .map(BankMovementMapper.getInstance()::fromBankMovementEntityToBankMovement)
                .map(BankMovementMapper.getInstance()::fromBankMovementToBankMovementDto)
                .toList();
    }

    @Override
    public List<BankMovementDto> findByOriginCreditCardNumber(Long cardNumber) {
        return bankMovementRepository
                .findByOriginCreditCardNumber(cardNumber)
                .stream()
                .map(BankMovementMapper.getInstance()::fromBankMovementEntityToBankMovement)
                .map(BankMovementMapper.getInstance()::fromBankMovementToBankMovementDto)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(findById(id).isEmpty()){
            throw new ResourceNotFoundException("No movement found with id "+id);
        }
        bankMovementRepository.deleteById(id);
    }
}
