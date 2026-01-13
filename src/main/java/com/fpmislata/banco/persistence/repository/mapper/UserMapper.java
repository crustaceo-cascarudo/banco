package com.fpmislata.banco.persistence.repository.mapper;

import com.fpmislata.banco.domain.repository.entity.UserEntity;
import com.fpmislata.banco.persistence.dao.impl.entity.UserJpaEntity;

public class UserMapper {

    private static UserMapper instance;

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    public UserJpaEntity fromUserEntitytoJpaEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserJpaEntity(
                userEntity.id(),
                userEntity.name(),
                userEntity.surname1(),
                userEntity.surname2(),
                userEntity.dni(),
                userEntity.passwordHash()
        );
    }

    public UserEntity fromUserJpaEntitytoUserEntity(UserJpaEntity userJpaEntity) {
        if (userJpaEntity == null) {
            return null;
        }
        return new UserEntity(
                userJpaEntity.getId(),
                userJpaEntity.getName(),
                userJpaEntity.getSurname1(),
                userJpaEntity.getSurname2(),
                userJpaEntity.getDni(),
                userJpaEntity.getPasswordHash()
        );
    }
}
