package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.web.webModel.request.TransferRequest;
import com.fpmislata.banco.domain.Exception.BusinessException;
import com.fpmislata.banco.domain.enums.MovementType;
import com.fpmislata.banco.domain.enums.PaymentMethod;
import com.fpmislata.banco.domain.mapper.BankAccountMapper;
import com.fpmislata.banco.domain.model.BankAccount;
import com.fpmislata.banco.domain.service.BankAccountService;
import com.fpmislata.banco.domain.service.BankMovementService;
import com.fpmislata.banco.domain.service.TransferService;
import com.fpmislata.banco.domain.service.dto.BankAccountDto;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import jakarta.transaction.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class TransferServiceImpl implements TransferService {

  private final BankAccountService bankAccountService;
  private final BankMovementService bankMovementService;

  public TransferServiceImpl(BankAccountService bankAccountService, BankMovementService bankMovementService) {
    this.bankAccountService = bankAccountService;
    this.bankMovementService = bankMovementService;
  }

  @Override
  @Transactional
  public BankMovementDto processTransfer(TransferRequest transferRequest) {
    BankAccountDto originAccount = bankAccountService.findByIban(transferRequest.bankMovement().originAccountIban())
        .orElseThrow(() -> new BusinessException("Origin account not found"));

    BankAccountDto recipientAccount = bankAccountService
        .findByIban(transferRequest.bankMovement().recipientAccountIban())
        .orElseThrow(() -> new BusinessException("Recipient account not found"));

    List<BankAccountDto> userAccounts = bankAccountService.findByUserId(transferRequest.user().id());

    if (userAccounts.isEmpty()) {
      throw new BusinessException("User doesn't have any bank account");
    }

    if (userAccounts.stream().noneMatch(account -> account.iban().equals(originAccount.iban()))) {
      throw new BusinessException("Origin account does not belong to the user");
    }

    if (originAccount.iban().equals(recipientAccount.iban())) {
      throw new BusinessException("Origin and recipient accounts must be different");
    }

    if (transferRequest.bankMovement().amount() < 0) {
      throw new BusinessException("Transfer amount must be positive");
    }

    if (originAccount.balance() < transferRequest.bankMovement().amount()) {
      throw new BusinessException("Insufficient funds in origin account");
    }

    if (transferRequest.bankMovement().concept().length() < 3) {
      throw new BusinessException("Concept must be at least 3 characters long");
    }

    BankAccount updatedOriginAccount = BankAccountMapper.getInstance().fromBankAccountDtoToBankAccount(originAccount);
    updatedOriginAccount.setBalance(updatedOriginAccount.getBalance() - transferRequest.bankMovement().amount());

    BankAccount updatedRecipientAccount = BankAccountMapper.getInstance()
        .fromBankAccountDtoToBankAccount(recipientAccount);
    updatedRecipientAccount.setBalance(updatedRecipientAccount.getBalance() + transferRequest.bankMovement().amount());

    BankMovementDto bankMovementDto = new BankMovementDto(
        null,
        MovementType.DEBIT,
        PaymentMethod.CARD_PAYMENT,
        originAccount.iban(),
        null,
        recipientAccount.iban(),
        Date.valueOf(LocalDate.now()),
        transferRequest.bankMovement().amount(),
        transferRequest.bankMovement().concept());

    bankAccountService.update(BankAccountMapper.getInstance().fromBankAccountToBankAccountDto(updatedOriginAccount));
    bankAccountService.update(BankAccountMapper.getInstance().fromBankAccountToBankAccountDto(updatedRecipientAccount));
    return bankMovementService.create(bankMovementDto);
  }
}
