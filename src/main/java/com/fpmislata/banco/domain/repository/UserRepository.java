package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> findByDni(String dni);
    UserEntity logByDni(String dni);
    void delete(Long id);
    String createSessionToken(Long userId);
    UserEntity findByToken(String token);
    void deleteSessionToken(String token);
}
