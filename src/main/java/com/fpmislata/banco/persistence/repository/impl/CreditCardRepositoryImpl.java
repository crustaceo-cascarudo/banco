package com.fpmislata.banco.persistence.repository.impl;

import com.fpmislata.banco.domain.repository.CreditCardRepository;
import com.fpmislata.banco.domain.repository.entity.CreditCardEntity;
import com.fpmislata.banco.persistence.dao.CreditCardDao;
import com.fpmislata.banco.persistence.dao.impl.entity.CreditCardJpaEntity;
import com.fpmislata.banco.persistence.repository.mapper.CreditCardMapper;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class CreditCardRepositoryImpl implements CreditCardRepository {
    private final CreditCardDao creditCardDao;

    public CreditCardRepositoryImpl(CreditCardDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    @Override
    @Transactional
    public CreditCardEntity update(CreditCardEntity cardEntity) {
        CreditCardJpaEntity entity = CreditCardMapper.getInstance().fromCreditCardEntityToCreditCardJpaEntity(cardEntity);
        return CreditCardMapper.getInstance().fromCreditCardJpaEntityToCreditCardEntity(creditCardDao.update(entity));
    }

    @Override
    @Transactional
    public CreditCardEntity save(CreditCardEntity cardEntity) {
        CreditCardJpaEntity entity = CreditCardMapper.getInstance().fromCreditCardEntityToCreditCardJpaEntity(cardEntity);
        return CreditCardMapper.getInstance().fromCreditCardJpaEntityToCreditCardEntity(creditCardDao.insert(entity));
    }

    @Override
    public Optional<CreditCardEntity> findByCardNumber(Long cardNumber) {
        return creditCardDao.findByCardNumber(cardNumber).map(CreditCardMapper.getInstance()::fromCreditCardJpaEntityToCreditCardEntity);
    }

    @Override
    public List<CreditCardEntity> findByAccountIban(String iban) {
        return creditCardDao.findByAccountIban(iban).stream().map(CreditCardMapper.getInstance()::fromCreditCardJpaEntityToCreditCardEntity).toList();
    }

    @Override
    public void deleteByCardNumber(Long cardNumber) {
        creditCardDao.delete(cardNumber);
    }
}
