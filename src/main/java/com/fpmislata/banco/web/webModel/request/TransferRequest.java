package com.fpmislata.banco.web.webModel.request;

import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import com.fpmislata.banco.domain.service.dto.UserDto;

public record TransferRequest(
    UserDto user,
    String apiToken,
    BankMovementDto bankMovement) {
}
