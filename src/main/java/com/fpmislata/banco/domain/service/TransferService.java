package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.web.webModel.request.TransferRequest;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;

public interface TransferService {
  BankMovementDto processTransfer(TransferRequest transferRequest);
}
