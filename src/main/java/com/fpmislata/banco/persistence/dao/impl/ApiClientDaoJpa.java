
package com.fpmislata.banco.persistence.dao.impl;

import com.fpmislata.banco.persistence.dao.ApiClientDao;
import com.fpmislata.banco.persistence.dao.impl.entity.ApiClientJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ApiClientDaoJpa implements ApiClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ApiClientJpaEntity insert(ApiClientJpaEntity apiClientJpaEntity) {
        entityManager.persist(apiClientJpaEntity);
        return apiClientJpaEntity;
    }

    @Override
    public ApiClientJpaEntity update(ApiClientJpaEntity apiClientJpaEntity) {
        return entityManager.merge(apiClientJpaEntity);
    }

    @Override
    public void delete(Long id) {
        ApiClientJpaEntity entity = entityManager.find(ApiClientJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public Optional<ApiClientJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(ApiClientJpaEntity.class, id));
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(a) FROM ApiClientJpaEntity a", Long.class)
                .getSingleResult();
    }

    @Override
    public Optional<ApiClientJpaEntity> findByApiKeyHash(String apiKeyHash) {
        String jpql = "SELECT a FROM ApiClientJpaEntity a WHERE a.apiKeyHash = :apiKeyHash";
        List<ApiClientJpaEntity> clients = entityManager.createQuery(jpql, ApiClientJpaEntity.class)
                .setParameter("apiKeyHash", apiKeyHash)
                .getResultList();
        return clients.isEmpty() ? Optional.empty() : Optional.of(clients.get(0));
    }

    @Override
    public List<ApiClientJpaEntity> findAll() {
        String jpql = "SELECT a FROM ApiClientJpaEntity a";
        return entityManager.createQuery(jpql, ApiClientJpaEntity.class)
                .getResultList();
    }
}
