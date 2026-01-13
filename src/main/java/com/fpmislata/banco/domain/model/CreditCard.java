package com.fpmislata.banco.domain.model;

import java.util.Date;

public class CreditCard {
    private Long cardNumber;
    private Date expirationDate;
    private int cvc;
    private String fullName;
    private String accountIban;

    public CreditCard(Long cardNumber, Date expirationDate, int cvc, String fullName, String accountIban) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
        this.fullName = fullName;
        this.accountIban = accountIban;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountIban() {
        return accountIban;
    }

    public void setAccountIban(String accountIban) {
        this.accountIban = accountIban;
    }
}
