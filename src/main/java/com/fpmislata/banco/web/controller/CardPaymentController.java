package com.fpmislata.banco.web.controller;

import com.fpmislata.banco.web.webModel.request.CardPaymentRequest;
import com.fpmislata.banco.domain.service.CardPaymentService;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card-payment")
public class CardPaymentController {
  private final CardPaymentService cardPaymentService;

  public CardPaymentController(CardPaymentService cardPaymentService) {
    this.cardPaymentService = cardPaymentService;
  }

  @PostMapping
  public ResponseEntity<BankMovementDto> processCardPayment(@RequestBody CardPaymentRequest cardPaymentRequest) {
    return ResponseEntity.ok(cardPaymentService.processCardPayment(cardPaymentRequest));
  }
}
