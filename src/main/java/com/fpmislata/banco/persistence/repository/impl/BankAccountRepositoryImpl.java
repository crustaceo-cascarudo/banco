package com.fpmislata.banco.persistence.repository.impl;

import com.fpmislata.banco.domain.repository.BankAccountRepository;
import com.fpmislata.banco.domain.repository.entity.BankAccountEntity;
import com.fpmislata.banco.persistence.dao.BankAccountDao;
import com.fpmislata.banco.persistence.dao.impl.entity.BankAccountJpaEntity;
import com.fpmislata.banco.persistence.repository.mapper.BankAccountMapper;

import java.util.List;
import java.util.Optional;

public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final BankAccountDao bankAccountDao;

    public BankAccountRepositoryImpl(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    @Override
    public BankAccountEntity update(BankAccountEntity bankAccountEntity) {
        BankAccountJpaEntity entity = BankAccountMapper.getInstance().fromBankAccountEntityToBankAccountJpaEntity(bankAccountEntity);
        return BankAccountMapper.getInstance().fromBankAccountJpaEntityToBankAccountEntity(bankAccountDao.update(entity));
    }

    @Override
    public BankAccountEntity save(BankAccountEntity bankAccountEntity) {
        BankAccountJpaEntity entity = BankAccountMapper.getInstance().fromBankAccountEntityToBankAccountJpaEntity(bankAccountEntity);
        return BankAccountMapper.getInstance().fromBankAccountJpaEntityToBankAccountEntity(bankAccountDao.insert(entity));
    }

    @Override
    public void delete(String iban) {
        bankAccountDao.delete(iban);
    }

    @Override
    public Optional<BankAccountEntity> findByIban(String iban) {
        return bankAccountDao.findByIban(iban).map(BankAccountMapper.getInstance()::fromBankAccountJpaEntityToBankAccountEntity);
    }

    @Override
    public List<BankAccountEntity> findByUserId(Long userId) {
        return bankAccountDao.findByUserId(userId).stream().map(BankAccountMapper.getInstance()::fromBankAccountJpaEntityToBankAccountEntity                   ).toList();
    }
}
