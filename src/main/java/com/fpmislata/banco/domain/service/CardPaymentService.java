package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.controller.webModel.request.CardPaymentRequest;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;

public interface CardPaymentService {
    BankMovementDto processCardPayment(CardPaymentRequest cardPaymentRequest);
}
