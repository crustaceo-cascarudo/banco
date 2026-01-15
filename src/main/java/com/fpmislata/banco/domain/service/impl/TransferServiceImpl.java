package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.Exception.BusinessException;
import com.fpmislata.banco.domain.mapper.BankAccountMapper;
import com.fpmislata.banco.domain.model.BankAccount;
import com.fpmislata.banco.domain.service.BankAccountService;
import com.fpmislata.banco.domain.service.BankMovementService;
import com.fpmislata.banco.domain.service.TransferService;
import com.fpmislata.banco.domain.service.dto.BankAccountDto;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TransferServiceImpl implements TransferService {

    private final BankAccountService bankAccountService;
    private final BankMovementService bankMovementService;

    public TransferServiceImpl(BankAccountService bankAccountService, BankMovementService bankMovementService) {
        this.bankAccountService = bankAccountService;
        this.bankMovementService = bankMovementService;
    }

    @Override
    @Transactional
    public BankMovementDto processTransfer(BankMovementDto bankMovementDto) {
        BankAccountDto originAccount = bankAccountService.findByIban(bankMovementDto.originAccountIban())
                .orElseThrow(() -> new BusinessException("Origin account not found"));

        BankAccountDto recipientAccount = bankAccountService.findByIban(bankMovementDto.recipientAccountIban())
                .orElseThrow(() -> new BusinessException("Recipient account not found"));

        if(originAccount.iban().equals(recipientAccount.iban())){
            throw new BusinessException("Origin and recipient accounts must be different");
        }

        if(bankMovementDto.amount()<0){
            throw new BusinessException("Transfer amount must be positive");
        }

        if (originAccount.balance() < bankMovementDto.amount()) {
            throw new BusinessException("Insufficient funds in origin account");
        }

        BankAccount updatedOriginAccount = BankAccountMapper.getInstance().fromBankAccountDtoToBankAccount(originAccount);
        updatedOriginAccount.setBalance(updatedOriginAccount.getBalance() - bankMovementDto.amount());

        BankAccount updatedRecipientAccount = BankAccountMapper.getInstance().fromBankAccountDtoToBankAccount(recipientAccount);
        updatedRecipientAccount.setBalance(updatedRecipientAccount.getBalance() + bankMovementDto.amount());

        bankAccountService.update(BankAccountMapper.getInstance().fromBankAccountToBankAccountDto(updatedOriginAccount));
        bankAccountService.update(BankAccountMapper.getInstance().fromBankAccountToBankAccountDto(updatedRecipientAccount));
        return bankMovementService.create(bankMovementDto);
    }
}
