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
import java.util.UUID;

public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public BankAccountDto create(Long userId) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity(
                generateIban(),
                0.0,
                userId
        );

        if(findByIban(bankAccountEntity.iban()).isPresent()){
            create(userId);
        }

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
        if (findByIban(iban).get().balance() > 0){
            throw new BusinessException("Cannot delete bank account with iban "+iban+" because it has a positive balance.");
        }
        bankAccountRepository.delete(iban);
    }

    private String generateIban(){
        StringBuilder newIban = new StringBuilder("ES");
        int bankNumber = 7564;
        int sucursalNumber = 2437;
        int checkNumber1 = (int) (10+Math.random()*100);
        int checkNumber2 = (int) (10+Math.random()*100);

        StringBuilder bban = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int r = (int) (Math.random()*(10));
            bban.append(r);
        }

        newIban.append(checkNumber1)
                .append(bankNumber)
                .append(sucursalNumber)
                .append(checkNumber2)
                .append(bban);

        return newIban.toString();
    }
}
