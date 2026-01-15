package com.fpmislata.banco.controller;

import com.fpmislata.banco.controller.webModel.request.TransferRequest;
import com.fpmislata.banco.domain.service.TransferService;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<BankMovementDto> processTransfer(@RequestBody TransferRequest transferRequest) {
        return ResponseEntity.ok(transferService.processTransfer(transferRequest));
    }
}
