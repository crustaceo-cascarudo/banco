package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.controller.webModel.request.CardPaymentRequest;
import com.fpmislata.banco.domain.Exception.BusinessException;
import com.fpmislata.banco.domain.enums.MovementType;
import com.fpmislata.banco.domain.enums.PaymentMethod;
import com.fpmislata.banco.domain.mapper.BankAccountMapper;
import com.fpmislata.banco.domain.model.BankAccount;
import com.fpmislata.banco.domain.service.BankAccountService;
import com.fpmislata.banco.domain.service.BankMovementService;
import com.fpmislata.banco.domain.service.CardPaymentService;
import com.fpmislata.banco.domain.service.CreditCardService;
import com.fpmislata.banco.domain.service.dto.BankAccountDto;
import com.fpmislata.banco.domain.service.dto.BankMovementDto;
import com.fpmislata.banco.domain.service.dto.CreditCardDto;
import jakarta.transaction.Transactional;

import java.sql.Date;
import java.time.LocalDate;

public class CardPaymentServiceImpl implements CardPaymentService {
    private final BankAccountService bankAccountService;
    private final BankMovementService bankMovementService;
    private final CreditCardService creditCardService;

    public CardPaymentServiceImpl(BankAccountService bankAccountService, BankMovementService bankMovementService, CreditCardService creditCardService) {
        this.bankAccountService = bankAccountService;
        this.bankMovementService = bankMovementService;
        this.creditCardService = creditCardService;
    }

    @Override
    @Transactional
    public BankMovementDto processCardPayment(CardPaymentRequest cardPaymentRequest) {
        BankAccountDto originAccount = bankAccountService.findByIban(cardPaymentRequest.originCreditCard().accountIban())
                .orElseThrow(() -> new BusinessException("Origin account not found"));

        BankAccountDto recipientAccount = bankAccountService.findByIban(cardPaymentRequest.recipientIban())
                .orElseThrow(() -> new BusinessException("Recipient account not found"));

        validateCreditcCard(cardPaymentRequest.originCreditCard());

        if(originAccount.iban().equals(recipientAccount.iban())){
            throw new BusinessException("Origin and recipient accounts must be different");
        }

        if(cardPaymentRequest.amount()<0){
            throw new BusinessException("Transfer amount must be positive");
        }

        if (originAccount.balance() < cardPaymentRequest.amount()) {
            throw new BusinessException("Insufficient funds in origin account");
        }

        if(cardPaymentRequest.concept().length()<3){
            throw new BusinessException("Concept must be at least 3 characters long");
        }

        BankAccount updatedOriginAccount = BankAccountMapper.getInstance().fromBankAccountDtoToBankAccount(originAccount);
        updatedOriginAccount.setBalance(updatedOriginAccount.getBalance() - cardPaymentRequest.amount());

        BankAccount updatedRecipientAccount = BankAccountMapper.getInstance().fromBankAccountDtoToBankAccount(recipientAccount);
        updatedRecipientAccount.setBalance(updatedRecipientAccount.getBalance() + cardPaymentRequest.amount());

        BankMovementDto bankMovementDto = new BankMovementDto(
                null,
                MovementType.DEBIT,
                PaymentMethod.CARD_PAYMENT,
                originAccount.iban(),
                cardPaymentRequest.originCreditCard().cardNumber(),
                recipientAccount.iban(),
                Date.valueOf(LocalDate.now()),
                cardPaymentRequest.amount(),
                cardPaymentRequest.concept()
        );

        bankAccountService.update(BankAccountMapper.getInstance().fromBankAccountToBankAccountDto(updatedOriginAccount));
        bankAccountService.update(BankAccountMapper.getInstance().fromBankAccountToBankAccountDto(updatedRecipientAccount));
        return bankMovementService.create(bankMovementDto);
    }

    private void validateCreditcCard(CreditCardDto cardDto){
        CreditCardDto foundByCardNumberAccount = creditCardService.findByCardNumber(cardDto.cardNumber()).orElseThrow(
                () -> new BusinessException("Credit card number not found")
        );
        if(!foundByCardNumberAccount.fullName().equalsIgnoreCase(cardDto.fullName())){
            throw new BusinessException("Card holder name does not match " + foundByCardNumberAccount.fullName() + " VS " + cardDto.fullName());
        }

        if(!foundByCardNumberAccount.accountIban().equals(cardDto.accountIban())){
            throw new BusinessException("Card account IBAN does not match " + foundByCardNumberAccount.accountIban() + " VS " + cardDto.accountIban());
        }

        if(foundByCardNumberAccount.cvc() != cardDto.cvc()){
            throw new BusinessException("CVC does not match " + foundByCardNumberAccount.cvc() + " VS " + cardDto.cvc());
        }

        LocalDate foundAccountDate = LocalDate.of(
                foundByCardNumberAccount.expirationDate().getYear(),
                foundByCardNumberAccount.expirationDate().getMonth(),
                foundByCardNumberAccount.expirationDate().getDay()
        );

         LocalDate cardDtoDate = LocalDate.of(
                 cardDto.expirationDate().getYear(),
                 cardDto.expirationDate().getMonth(),
                 cardDto.expirationDate().getDay()
         );

        if(!foundAccountDate.equals(cardDtoDate)){
            throw new BusinessException("Expiration date does not match " + foundAccountDate + " VS " + cardDtoDate);
        }

        if(cardDto.expirationDate().before(Date.valueOf(LocalDate.now()))){
            throw new BusinessException("Credit card is expired");
        }
    }
}
