package com.fpmislata.banco.persistence.repository.impl;

import com.fpmislata.banco.domain.repository.BankMovementRepository;
import com.fpmislata.banco.domain.repository.entity.BankMovementEntity;
import com.fpmislata.banco.persistence.dao.BankMovementDao;
import com.fpmislata.banco.persistence.dao.impl.entity.BankMovementJpaEntity;
import com.fpmislata.banco.persistence.repository.mapper.BankMovementMapper;

import java.util.List;
import java.util.Optional;

public class BankMovementRepositoryImpl implements BankMovementRepository {

    private final BankMovementDao bankMovementDao;

    public BankMovementRepositoryImpl(BankMovementDao bankMovementDao) {
        this.bankMovementDao = bankMovementDao;
    }

    @Override
    public BankMovementEntity save(BankMovementEntity bankMovementEntity) {
        BankMovementJpaEntity entity = BankMovementMapper.getInstance().fromBankMovementEntityToBankMovementJpaEntity(bankMovementEntity);
        if(entity.getId() == null){
            return BankMovementMapper.getInstance().fromBankMovementJpaEntityToBankMovementEntity(bankMovementDao.insert(entity));
        }
        return BankMovementMapper.getInstance().fromBankMovementJpaEntityToBankMovementEntity(bankMovementDao.update(entity));
    }

    @Override
    public Optional<BankMovementEntity> findById(Long id) {
        return bankMovementDao.findById(id).map(BankMovementMapper.getInstance()::fromBankMovementJpaEntityToBankMovementEntity);
    }

    @Override
    public List<BankMovementEntity> findByOriginAccountIban(String iban) {
        return bankMovementDao.findOriginAccountIban(iban).stream().map(BankMovementMapper.getInstance()::fromBankMovementJpaEntityToBankMovementEntity).toList();
    }

    @Override
    public List<BankMovementEntity> findByRecipientAccountIban(String iban) {
        return bankMovementDao.findByRecipientAccountIban(iban).stream().map(BankMovementMapper.getInstance()::fromBankMovementJpaEntityToBankMovementEntity).toList();
    }

    @Override
    public List<BankMovementEntity> findByOriginCreditCardNumber(Long cardNumber) {
        return bankMovementDao.findByOriginCreditCardNumber(cardNumber).stream().map(BankMovementMapper.getInstance()::fromBankMovementJpaEntityToBankMovementEntity).toList();
    }

    @Override
    public void deleteById(Long id) {
        bankMovementDao.delete(id);
    }
}
