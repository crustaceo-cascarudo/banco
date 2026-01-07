package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.service.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto update(UserDto userDto);
    String logByName(String name, String password); 
    void logout(String token);
    Optional<UserDto> findById(Long id);
    List<UserDto> findByName(String name);
    List<UserDto> findAll();
    void delete(Long id);
}
