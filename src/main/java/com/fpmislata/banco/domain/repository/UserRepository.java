package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
    List<UserEntity> findByName(String name);
    UserEntity logByName(String name);
    void delete(Long id);
    String createSessionToken(Long userId);
    UserEntity findByToken(String token);
    void deleteSessionToken(String token);
}
