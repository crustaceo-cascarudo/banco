package com.fpmislata.banco.controller.webModel.request;

import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import com.fpmislata.banco.domain.service.dto.UserDto;

public record TransferRequest(
        UserDto user,
        String apiToken,
        BankMovementDto bankMovement
) {
}
