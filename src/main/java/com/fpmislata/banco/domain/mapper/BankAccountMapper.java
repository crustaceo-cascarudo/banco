package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.BankAccount;
import com.fpmislata.banco.domain.repository.entity.BankAccountEntity;
import com.fpmislata.banco.domain.service.dto.BankAccountDto;

public class BankAccountMapper {

    private static BankAccountMapper instance;

    private BankAccountMapper() {
    }

    public static BankAccountMapper getInstance() {
        if (instance == null) {
            instance = new BankAccountMapper();
        }
        return instance;
    }

    public BankAccountDto fromBankAccountToBankAccountDto(BankAccount bankAccount) {
        if (bankAccount == null) {
            return null;
        }
        return new BankAccountDto(
                bankAccount.getIban(),
                bankAccount.getBalance(),
                bankAccount.getUserId()
        );
    }

    public BankAccount fromBankAccountDtoToBankAccount(BankAccountDto bankAccountDto) {
        if (bankAccountDto == null) {
            return null;
        }
        return new BankAccount(
                bankAccountDto.iban(),
                bankAccountDto.balance(),
                bankAccountDto.userId()
        );
    }

    public BankAccountEntity fromBankAccountToBankAccountEntity(BankAccount bankAccount) {
        if (bankAccount == null) {
            return null;
        }
        return new BankAccountEntity(
                bankAccount.getIban(),
                bankAccount.getBalance(),
                bankAccount.getUserId()
        );
    }

    public BankAccount fromBankAccountEntityToBankAccount(BankAccountEntity bankAccountEntity) {
        if (bankAccountEntity == null) {
            return null;
        }
        return new BankAccount(
                bankAccountEntity.iban(),
                bankAccountEntity.balance(),
                bankAccountEntity.userId()
        );
    }
}
