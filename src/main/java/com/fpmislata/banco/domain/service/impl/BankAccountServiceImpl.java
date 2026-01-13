package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.Exception.BusinessException;
import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.domain.mapper.BankAccountMapper;
import com.fpmislata.banco.domain.repository.BankAccountRepository;
import com.fpmislata.banco.domain.repository.entity.BankAccountEntity;
import com.fpmislata.banco.domain.service.BankAccountService;
import com.fpmislata.banco.domain.service.dto.BankAccountDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public BankAccountDto create(BankAccountDto bankAccountDto) {
        if(findByIban(bankAccountDto.iban()).isPresent()){
            throw new BusinessException("Bank account with iban '"+bankAccountDto.iban()+"' already exists");
        }

        BankAccountEntity bankAccountEntity = BankAccountMapper.getInstance().fromBankAccountToBankAccountEntity(
                BankAccountMapper.getInstance().fromBankAccountDtoToBankAccount(bankAccountDto)
        );

        return BankAccountMapper.getInstance().fromBankAccountToBankAccountDto(
                BankAccountMapper.getInstance().fromBankAccountEntityToBankAccount(
                        bankAccountRepository.save(bankAccountEntity)
                )
        );
    }

    @Override
    @Transactional
    public BankAccountDto update(BankAccountDto bankAccountDto) {
        bankAccountRepository.findByIban(bankAccountDto.iban())
                .orElseThrow(() -> new ResourceNotFoundException("No bank account found with iban "+bankAccountDto.iban()));

        BankAccountEntity bankAccountEntity = BankAccountMapper.getInstance().fromBankAccountToBankAccountEntity(
                BankAccountMapper.getInstance().fromBankAccountDtoToBankAccount(bankAccountDto)
        );

        return BankAccountMapper.getInstance().fromBankAccountToBankAccountDto(
                BankAccountMapper.getInstance().fromBankAccountEntityToBankAccount(
                        bankAccountRepository.save(bankAccountEntity)
                )
        );
    }

    @Override
    public Optional<BankAccountDto> findByIban(String iban) {
        return bankAccountRepository
                .findByIban(iban)
                .map(BankAccountMapper.getInstance()::fromBankAccountEntityToBankAccount)
                .map(BankAccountMapper.getInstance()::fromBankAccountToBankAccountDto);
    }


    @Override
    public List<BankAccountDto> findByUserId(Long userId) {
        return bankAccountRepository
                .findByUserId(userId)
                .stream()
                .map(BankAccountMapper.getInstance()::fromBankAccountEntityToBankAccount)
                .map(BankAccountMapper.getInstance()::fromBankAccountToBankAccountDto)
                .toList();
    }

    @Override
    @Transactional
    public void delete(String iban) {
        if(findByIban(iban).isEmpty()){
            throw new ResourceNotFoundException("No bank account found with iban "+iban);
        }
        bankAccountRepository.delete(iban);
    }
}
