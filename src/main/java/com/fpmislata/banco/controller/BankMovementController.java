package com.fpmislata.banco.controller;

import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.domain.service.BankMovementService;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
                () -> new ResourceNotFoundException("Bank movement with id " + id + " not found")
        ));
    }

    @GetMapping("/origin/{iban}")
    public ResponseEntity<List<BankMovementDto>> findByOriginAccountIban(@RequestParam String iban) {
        return ResponseEntity.ok(bankMovementService.findByOriginAccountIban(iban));
    }

    @GetMapping("/recipient/{iban}")
    public ResponseEntity<List<BankMovementDto>> findByRecipientAccountIban(@RequestParam String iban) {
        return ResponseEntity.ok(bankMovementService.findByRecipientAccountIban(iban));
    }

    @PostMapping
    public ResponseEntity<BankMovementDto> create(@RequestBody @Validated BankMovementDto bankMovementDto) {
        return ResponseEntity.ok(bankMovementService.create(bankMovementDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankMovementDto> update(
            @PathVariable Long id,
            @RequestBody BankMovementDto bankMovementDto
    ) {
        return ResponseEntity.ok(bankMovementService.update(bankMovementDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bankMovementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
