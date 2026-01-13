package com.fpmislata.banco.persistence.dao.impl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "card")
public class CreditCardJpaEntity {
    @Id
    private Long cardNumber;
    @NotNull
    private Date expirationDate;
    @NotNull
    private int cvc;
    @NotNull
    private String fullName;
    @NotNull
    private String accountIban;

    public CreditCardJpaEntity(){
    }

    public CreditCardJpaEntity(Long cardNumber, Date expirationDate, int cvc, String fullName, String accountIban) {
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
