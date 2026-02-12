package com.fpmislata.banco.web.controller;

import com.fpmislata.banco.web.mapper.UserMapper;
import com.fpmislata.banco.web.webModel.request.LoginUserRequest;
import com.fpmislata.banco.web.webModel.request.RegisterUserRequest;
import com.fpmislata.banco.web.webModel.response.LoginResponse;
import com.fpmislata.banco.web.webModel.response.UserResponse;
import com.fpmislata.banco.domain.service.BankAccountService;
import com.fpmislata.banco.domain.service.UserService;
import com.fpmislata.banco.domain.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
  private final BankAccountService bankAccountService;

  public UserController(UserService userService, BankAccountService bankAccountService) {
    this.userService = userService;
    this.bankAccountService = bankAccountService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(@RequestBody RegisterUserRequest request) {
    UserDto userDto = UserMapper.getInstance().fromUserRequestToUserDto(request);
    UserDto createdUser = userService.create(userDto);
    UserResponse response = UserMapper.getInstance().fromUserDtoToUserResponse(createdUser);

    if (bankAccountService.create(createdUser.id()) == null) {
      throw new IllegalStateException(
          "User created successfully. Error creating bank account for user with id: " + userDto.id() + ".");
    }

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginUserRequest request) {
    String token = userService.logByDni(request.dni(), request.plainPassword());
    List<UserDto> users = userService.findByDni(request.dni());
    if (users.isEmpty()) {
      throw new IllegalArgumentException("User not found");
    }
    UserResponse userResponse = UserMapper.getInstance().fromUserDtoToUserResponse(users.get(0));
    LoginResponse response = new LoginResponse(token, userResponse);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      throw new IllegalArgumentException("Invalid Authorization header format.");
    }

    String token = authHeader.substring(7);
    userService.logout(token);

    return ResponseEntity.noContent().build();

  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
    UserDto userDto = userService.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

    UserResponse response = UserMapper.getInstance().fromUserDtoToUserResponse(userDto);
    return ResponseEntity.ok(response);
  }

  /**
   * Obtiene el perfil del usuario autenticado a partir del token de sesión.
   * No requiere pasar el userId — se extrae del token en la cabecera Authorization.
   */
  @GetMapping("/me")
  public ResponseEntity<UserResponse> getMe(HttpServletRequest request) {
    Long userId = (Long) request.getAttribute("authenticatedUserId");
    if (userId == null) {
      throw new IllegalStateException("No se pudo identificar al usuario autenticado");
    }
    UserDto userDto = userService.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));
    UserResponse response = UserMapper.getInstance().fromUserDtoToUserResponse(userDto);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/search")
  public ResponseEntity<List<UserResponse>> findByDni(@RequestParam String dni) {
    List<UserDto> users = userService.findByDni(dni);
    List<UserResponse> response = users.stream()
        .map(UserMapper.getInstance()::fromUserDtoToUserResponse)
        .toList();
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody RegisterUserRequest request) {
    UserDto userDto = UserMapper.getInstance().fromUserRequestToUserDto(request);
    UserDto updatedUser = userService.update(new UserDto(
        id,
        userDto.name(),
        userDto.surname1(),
        userDto.surname2(),
        userDto.dni(),
        userDto.plainPassword(),
        userDto.passwordHash()));
    UserResponse response = UserMapper.getInstance().fromUserDtoToUserResponse(updatedUser);
    return ResponseEntity.ok(response);
  }
}
