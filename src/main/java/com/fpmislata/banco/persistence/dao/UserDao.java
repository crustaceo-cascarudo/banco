package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.persistence.dao.impl.entity.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<UserJpaEntity> {
    Optional<UserJpaEntity> findById(Long id);
    List<UserJpaEntity> findByDni(String dni);
    String createSessionToken(Long userId);
    UserJpaEntity findByToken(String token);
    void deleteToken(String token);
}