package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.mapper.UserMapper;
import com.fpmislata.banco.domain.repository.UserRepository;
import com.fpmislata.banco.domain.repository.entity.UserEntity;
import com.fpmislata.banco.domain.service.PasswordEncoderService;
import com.fpmislata.banco.domain.service.UserService;
import com.fpmislata.banco.domain.service.dto.UserDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoderService passwordEncoderService) {
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        List<UserDto> existingUsersByName = findByDni(userDto.dni());
        if (!existingUsersByName.isEmpty()) {
            throw new IllegalArgumentException("User with dni " + userDto.dni() + " already exists.");
        }
        String hashedpassword = passwordEncoderService.encode(userDto.plainPassword());
        userDto = new UserDto(
                null,
                userDto.name(),
                userDto.surname1(),
                userDto.surname2(),
                userDto.dni(),
                userDto.plainPassword(),
                hashedpassword
                );
        UserEntity userEntity = UserMapper.getInstance()
                .fromUserToUserEntity(UserMapper.getInstance().fromUserDtoToUser(userDto));
        userEntity = userRepository.save(userEntity);
        return UserMapper.getInstance().fromUserToUserDto(UserMapper.getInstance().fromUserEntityToUser(userEntity));
    }

    @Override
    @Transactional
    public UserDto update(UserDto userDto) {
        Optional<UserDto> existingUser = findById(userDto.id());
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("User with id " + userDto.id() + " does not exist.");
        }
        UserEntity userEntity = UserMapper.getInstance()
                .fromUserToUserEntity(UserMapper.getInstance().fromUserDtoToUser(userDto));
        userRepository.save(userEntity);
        return UserMapper.getInstance().fromUserToUserDto(UserMapper.getInstance().fromUserEntityToUser(userEntity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<UserDto> existingUser = findById(id);
        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " does not exist.");
        }
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public String logByDni(String dni, String password) {
        List<UserDto> existingUsers = findByDni(dni);
        if (existingUsers.isEmpty()) {
            throw new IllegalArgumentException("User with DNI " + dni + " does not exist.");
        }
        boolean passwordMatches = passwordEncoderService.verify(password, existingUsers.get(0).passwordHash());
        if (!passwordMatches) {
            throw new IllegalArgumentException("Incorrect password for user with DNI" + dni + ".");
        }

        // Crear token de sesión
        String sessionToken = userRepository.createSessionToken(existingUsers.get(0).id());

        // Devolver DTO con usuario y token
        return sessionToken;
    }

    @Override
    @Transactional
    public void logout(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        UserEntity user = userRepository.findByToken(token);
        if (user == null) {
            throw new IllegalArgumentException("Invalid token or session already expired");
        }
        userRepository.deleteSessionToken(token);
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper.getInstance()::fromUserEntityToUser)
                .map(UserMapper.getInstance()::fromUserToUserDto);
    }

    @Override
    public List<UserDto> findByDni(String dni) {
        return userRepository.findByDni(dni).stream()
                .map(UserMapper.getInstance()::fromUserEntityToUser)
                .map(UserMapper.getInstance()::fromUserToUserDto)
                .toList();
    }
}
