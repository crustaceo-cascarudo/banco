package com.fpmislata.banco.web.controller;

import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.domain.service.BankMovementService;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-movements")
public class BankMovementController {
  private final BankMovementService bankMovementService;

  public BankMovementController(BankMovementService bankMovementService) {
    this.bankMovementService = bankMovementService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BankMovementDto> findById(@PathVariable Long id) {
    return ResponseEntity.ok(bankMovementService.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Bank movement with id " + id + " not found")));
  }

  @GetMapping("/origin/{iban}")
  public ResponseEntity<List<BankMovementDto>> findByOriginAccountIban(@PathVariable String iban) {
    return ResponseEntity.ok(bankMovementService.findByOriginAccountIban(iban));
  }

  @GetMapping("/recipient/{iban}")
  public ResponseEntity<List<BankMovementDto>> findByRecipientAccountIban(@PathVariable String iban) {
    return ResponseEntity.ok(bankMovementService.findByRecipientAccountIban(iban));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BankMovementDto> update(
      @PathVariable Long id,
      @RequestBody BankMovementDto bankMovementDto) {
    return ResponseEntity.ok(bankMovementService.update(bankMovementDto));
  }
}
