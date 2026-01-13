package com.fpmislata.banco.persistence.dao.impl;

import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.persistence.dao.CreditCardDao;
import com.fpmislata.banco.persistence.dao.impl.entity.CreditCardJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class CreditCardDaoJpa implements CreditCardDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CreditCardJpaEntity> findByCardNumber(Long cardNumber) {
        return Optional.ofNullable(entityManager.find(CreditCardJpaEntity.class, cardNumber));
    }

    @Override
    public List<CreditCardJpaEntity> findByAccountIban(String accountIban) {
        String sql = "SELECT b FROM CreditCardJpaEntity b WHERE b.accountIban = :accountIban";
        try{
            return entityManager.createQuery(sql, CreditCardJpaEntity.class)
                    .setParameter("accountIban", accountIban)
                    .getResultList();
        } catch (Exception e){
            return List.of();
        }
    }

    @Override
    public CreditCardJpaEntity insert(CreditCardJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public CreditCardJpaEntity update(CreditCardJpaEntity jpaEntity) {
        CreditCardJpaEntity managed = entityManager.find(CreditCardJpaEntity.class, jpaEntity.getCardNumber());
        if (managed == null) {
            throw new ResourceNotFoundException("Crad not found with card number " + jpaEntity.getCardNumber());
        }
        entityManager.flush();
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void delete(Long cardNumber) {
        entityManager.remove(entityManager.find(CreditCardJpaEntity.class, cardNumber));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(b) FROM CreditCardJpaEntity b", Long.class).getSingleResult();
    }
}
