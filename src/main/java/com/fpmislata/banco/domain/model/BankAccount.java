package com.fpmislata.banco.domain.model;

public class BankAccount {
    private String iban;
    private Double balance;
    private Long userId;

    public BankAccount(String iban, Double balance, Long userId) {
        this.iban = iban;
        this.balance = balance;
        this.userId = userId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
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
