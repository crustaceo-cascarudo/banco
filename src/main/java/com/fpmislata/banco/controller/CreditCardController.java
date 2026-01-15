package com.fpmislata.banco.controller;


import com.fpmislata.banco.controller.webModel.request.CreateCreditCardRequest;
import com.fpmislata.banco.domain.Exception.ResourceNotFoundException;
import com.fpmislata.banco.domain.service.CreditCardService;
import com.fpmislata.banco.domain.service.dto.CreditCardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<CreditCardDto> findByCreditCardNumber(@PathVariable Long cardNumber) {
        return ResponseEntity.ok(creditCardService.findByCardNumber(cardNumber).orElseThrow(
                () -> new ResourceNotFoundException("Credit card number " + cardNumber + " not found")
        ));
    }

    @GetMapping("/account/{iban}")
    public ResponseEntity<List<CreditCardDto>> findByAccountIban(@PathVariable String iban) {
        return ResponseEntity.ok(creditCardService.findByAccountIban(iban));
    }

    @PostMapping
    public ResponseEntity<CreditCardDto> create(@RequestBody CreateCreditCardRequest request) {
        return ResponseEntity.ok(creditCardService.create(request));
    }

    @PutMapping("/{cardNumber}")
    public ResponseEntity<CreditCardDto> update(
            @PathVariable Long cardNumber,
            @RequestBody CreditCardDto creditCardDto
    ) {
        return ResponseEntity.ok(creditCardService.update(creditCardDto));
    }

    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<Void> delete(@PathVariable Long cardNumber) {
        creditCardService.deleteByCardNumber(cardNumber);
        return ResponseEntity.noContent().build();
    }
}
