package com.fpmislata.banco.persistence.dao.impl;

import com.fpmislata.banco.persistence.dao.UserDao;
import com.fpmislata.banco.persistence.dao.impl.entity.SessionTokenJpaEntity;
import com.fpmislata.banco.persistence.dao.impl.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDaoJpa implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void delete(Long id) {
        UserJpaEntity entity = entityManager.find(UserJpaEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public List<UserJpaEntity> findByDni(String dni) {
        String jpql = "SELECT u FROM UserJpaEntity u WHERE u.dni = :dni";
        return entityManager.createQuery(jpql, UserJpaEntity.class)
                .setParameter("dni", dni)
                .getResultList();
    }

    @Override
    public Optional<UserJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserJpaEntity.class, id));
    }

    @Override
    public UserJpaEntity insert(UserJpaEntity userJpaEntity) {
        entityManager.persist(userJpaEntity);
        entityManager.flush();
        return userJpaEntity;
    }

    @Override
    public UserJpaEntity update(UserJpaEntity userJpaEntity) {
        return entityManager.merge(userJpaEntity);
    }

    @Override
    public String createSessionToken(Long userId) {
        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        SessionTokenJpaEntity sessionToken = new SessionTokenJpaEntity(token, userId, now);

        entityManager.persist(sessionToken);
        entityManager.flush();

        return token;
    }

    @Override
    public UserJpaEntity findByToken(String token) {
        String jpql = "SELECT u FROM UserJpaEntity u JOIN SessionTokenJpaEntity s ON u.id = s.userId WHERE s.token = :token";
        List<UserJpaEntity> users = entityManager.createQuery(jpql, UserJpaEntity.class)
                .setParameter("token", token)
                .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void deleteToken(String token) {
        SessionTokenJpaEntity sessionToken = entityManager.find(SessionTokenJpaEntity.class, token);
        if (sessionToken != null) {
            entityManager.remove(sessionToken);
        }
    }

    @Override
    public long count() {
        return entityManager.createQuery("SELECT COUNT(u) FROM UserJpaEntity u", Long.class)
                .getSingleResult();
    }
}
