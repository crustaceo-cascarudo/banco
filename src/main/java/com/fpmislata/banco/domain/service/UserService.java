package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    String logByDni(String dni, String password);
    void logout(String token);
    Optional<UserDto> findById(Long id);
    List<UserDto> findByDni(String dni);
    void delete(Long id);
}
