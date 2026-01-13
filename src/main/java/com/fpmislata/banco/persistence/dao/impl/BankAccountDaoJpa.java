package com.fpmislata.banco.persistence.dao.impl;

import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.persistence.dao.BankAccountDao;
import com.fpmislata.banco.persistence.dao.impl.entity.BankAccountJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class BankAccountDaoJpa implements BankAccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<BankAccountJpaEntity> findByIban(String iban) {
        return Optional.ofNullable(entityManager.find(BankAccountJpaEntity.class, iban));
    }

    @Override
    public List<BankAccountJpaEntity> findByUserId(Long userId) {
        String sql = "SELECT b FROM BankAccountJpaEntity b WHERE b.userId = :userId";
        try {
            return entityManager.createQuery(sql, BankAccountJpaEntity.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public void delete(String iban) {
        entityManager.remove(entityManager.find(BankAccountJpaEntity.class, iban));
    }

    @Override
    public BankAccountJpaEntity insert(BankAccountJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public BankAccountJpaEntity update(BankAccountJpaEntity jpaEntity) {
        BankAccountJpaEntity managed = entityManager.find(BankAccountJpaEntity.class, jpaEntity.getIban());
        if (managed == null) {
            throw new ResourceNotFoundException("Bank account not found with iban " + jpaEntity.getIban());
        }
        entityManager.flush();
        return entityManager.merge(jpaEntity);
    }

    @Override
    public void delete(Long iban) {
        entityManager.remove(entityManager.find(BankAccountJpaEntity.class, iban));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(b) FROM BankAccountJpaEntity b", Long.class).getSingleResult();
    }
}
