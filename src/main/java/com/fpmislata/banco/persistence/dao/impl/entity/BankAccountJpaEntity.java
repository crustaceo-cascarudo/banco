package com.fpmislata.banco.persistence.dao.impl.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class BankAccountJpaEntity {
    @Id
    private String iban;
    @NotNull
    private Double balance;
    @NotNull
    private Long userId;

    public BankAccountJpaEntity(){
    }

    public BankAccountJpaEntity(String iban, Double balance, Long userId) {
        this.iban = iban;
        this.balance = balance == null? 0.0 : balance;
        this.userId = userId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
