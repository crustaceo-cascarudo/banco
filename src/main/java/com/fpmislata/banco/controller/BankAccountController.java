package com.fpmislata.banco.controller;

import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.domain.service.BankAccountService;
import com.fpmislata.banco.domain.service.dto.BankAccountDto;
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
                () -> new ResourceNotFoundException("Bank account with IBAN " + iban + " not found")
        ));
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<BankAccountDto>> findByUserID(@PathVariable("userID") Long userID) {
        if (bankAccountService.findByUserId(userID).isEmpty() || bankAccountService.findByUserId(userID) == null) {
            throw new ResourceNotFoundException("No bank account associated to userID " + userID + " found");
        }
        return ResponseEntity.ok(bankAccountService.findByUserId(userID));
    }

    @PostMapping
    public ResponseEntity<BankAccountDto> create(@RequestBody @Validated BankAccountDto bankAccountDto) {
        return ResponseEntity.ok(bankAccountService.create(bankAccountDto));
    }

    @PutMapping("/{iban}")
    public ResponseEntity<BankAccountDto> update(
            @PathVariable String iban,
            @RequestBody @Validated BankAccountDto bankAccountDto) {
        return ResponseEntity.ok(bankAccountService.update(bankAccountDto));
    }

    @DeleteMapping("/{iban}")
    public  ResponseEntity<BankAccountDto> delete(@PathVariable String iban) {
        bankAccountService.delete(iban);
        return  ResponseEntity.noContent().build();
    }
}
