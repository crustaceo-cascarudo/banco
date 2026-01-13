package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.User;
import com.fpmislata.banco.domain.repository.entity.UserEntity;
import com.fpmislata.banco.domain.service.dto.UserDto;

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

    public UserDto fromUserToUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname1(),
                user.getSurname2(),
                user.getDni(),
                null,
                user.getPasswordHash()
        );
    }

    public User fromUserDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new User(
                userDto.id(),
                userDto.name(),
                userDto.surname1(),
                userDto.surname2(),
                userDto.dni(),
                userDto.passwordHash()
        );
    }

    public UserEntity fromUserToUserEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getSurname1(),
                user.getSurname2(),
                user.getDni(),
                user.getPasswordHash()
        );
    }

    public User fromUserEntityToUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new User(
                userEntity.id(),
                userEntity.name(),
                userEntity.surname1(),
                userEntity.surname2(),
                userEntity.dni(),
                userEntity.passwordHash()
        );
    }
}
