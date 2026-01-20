package com.fpmislata.banco.persistence.dao.impl;

import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.persistence.dao.BankMovementDao;
import com.fpmislata.banco.persistence.dao.impl.entity.BankMovementJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class BankMovementDaoJpa implements BankMovementDao {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Optional<BankMovementJpaEntity> findById(Long id) {
    return Optional.ofNullable(entityManager.find(BankMovementJpaEntity.class, id));
  }

  @Override
  public List<BankMovementJpaEntity> findOriginAccountIban(String iban) {
    String sql = "SELECT b FROM BankMovementJpaEntity b WHERE b.originAccountIban = :iban";
    try {
      return entityManager.createQuery(sql, BankMovementJpaEntity.class)
          .setParameter("iban", iban)
          .getResultList();
    } catch (Exception e) {
      return List.of();
    }
  }

  @Override
  public List<BankMovementJpaEntity> findByRecipientAccountIban(String iban) {
    String sql = "SELECT b FROM BankMovementJpaEntity b WHERE b.recipientAccountIban = :iban";
    try {
      return entityManager.createQuery(sql, BankMovementJpaEntity.class)
          .setParameter("iban", iban)
          .getResultList();
    } catch (Exception e) {
      return List.of();
    }
  }

  @Override
  public List<BankMovementJpaEntity> findByOriginCreditCardNumber(Long cardNumber) {
    String sql = "SELECT b FROM BankMovementJpaEntity b WHERE b.originCreditCardNumber = :cardNumber";
    try {
      return entityManager.createQuery(sql, BankMovementJpaEntity.class)
          .setParameter("cardNumber", cardNumber)
          .getResultList();
    } catch (Exception e) {
      return List.of();
    }
  }

  @Override
  public BankMovementJpaEntity insert(BankMovementJpaEntity jpaEntity) {
    entityManager.persist(jpaEntity);
    return jpaEntity;
  }

  @Override
  public BankMovementJpaEntity update(BankMovementJpaEntity jpaEntity) {
    BankMovementJpaEntity managed = entityManager.find(BankMovementJpaEntity.class, jpaEntity.getId());
    if (managed == null) {
      throw new ResourceNotFoundException("Bank movement not found with id " + jpaEntity.getId());
    }
    entityManager.flush();
    return entityManager.merge(jpaEntity);
  }

  @Override
  public void delete(Long id) {
    entityManager.remove(entityManager.find(BankMovementJpaEntity.class, id));
  }

  @Override
  public long count() {
    return entityManager.createQuery("SELECT COUNT(b) FROM BankMovementJpaEntity b", Long.class).getSingleResult();
  }
}
