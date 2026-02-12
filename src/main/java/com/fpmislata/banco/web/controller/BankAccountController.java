package com.fpmislata.banco.web.controller;

import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.domain.service.BankAccountService;
import com.fpmislata.banco.domain.service.dto.BankAccountDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {
  private final BankAccountService bankAccountService;

  public BankAccountController(BankAccountService bankAccountService) {
    this.bankAccountService = bankAccountService;
  }

  @GetMapping("/{iban}")
  public ResponseEntity<BankAccountDto> findByIban(@PathVariable String iban) {
    return ResponseEntity.ok(bankAccountService.findByIban(iban).orElseThrow(
        () -> new ResourceNotFoundException("Bank account with IBAN " + iban + " not found")));
  }

  @GetMapping("/user/{userID}")
  public ResponseEntity<List<BankAccountDto>> findByUserID(@PathVariable("userID") Long userID) {
    List<BankAccountDto> accounts = bankAccountService.findByUserId(userID);
    if (accounts == null || accounts.isEmpty()) {
      throw new ResourceNotFoundException("No bank account associated to userID " + userID + " found");
    }
    return ResponseEntity.ok(accounts);
  }

  /**
   * Obtiene las cuentas del usuario autenticado a partir del token de sesión.
   * No requiere pasar el userId — se extrae del token en la cabecera Authorization.
   */
  @GetMapping("/me")
  public ResponseEntity<List<BankAccountDto>> findMyAccounts(HttpServletRequest request) {
    Long userId = (Long) request.getAttribute("authenticatedUserId");
    if (userId == null) {
      throw new IllegalStateException("No se pudo identificar al usuario autenticado");
    }
    List<BankAccountDto> accounts = bankAccountService.findByUserId(userId);
    if (accounts == null || accounts.isEmpty()) {
      throw new ResourceNotFoundException("No bank accounts found for authenticated user");
    }
    return ResponseEntity.ok(accounts);
  }

  @PostMapping
  public ResponseEntity<BankAccountDto> create(HttpServletRequest request) {
    Long userId = (Long) request.getAttribute("authenticatedUserId");
    if (userId == null) {
      throw new IllegalStateException("No se pudo identificar al usuario autenticado");
    }
    return ResponseEntity.ok(bankAccountService.create(userId));
  }

  @PutMapping("/{iban}")
  public ResponseEntity<BankAccountDto> update(
      @PathVariable String iban,
      @RequestBody @Validated BankAccountDto bankAccountDto) {
    return ResponseEntity.ok(bankAccountService.update(bankAccountDto));
  }

  @DeleteMapping("/{iban}")
  public ResponseEntity<BankAccountDto> delete(@PathVariable String iban) {
    bankAccountService.delete(iban);
    return ResponseEntity.noContent().build();
  }
}
