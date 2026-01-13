package com.fpmislata.banco.domain.model;

import com.fpmislata.banco.domain.enums.MovementType;
import com.fpmislata.banco.domain.enums.PaymentMethod;

import java.util.Date;

public class BankMovement {
    private Long id;
    private MovementType movementType;
    private PaymentMethod paymentMethod;
    private String originAccountIban;
    private Long originCreditCardNumber;
    private String recipientAccountIban;
    private Date movementDate;
    private float amount;
    private String concept;

    public BankMovement(Long id, MovementType movementType, PaymentMethod paymentMethod, String originAccountIban, Long originCreditCardNumber, String recipientAccountIban, Date movementDate, float amount, String concept) {
        this.id = id;
        this.movementType = movementType;
        this.paymentMethod = paymentMethod;
        this.originAccountIban = originAccountIban;
        this.originCreditCardNumber = originCreditCardNumber;
        this.recipientAccountIban = recipientAccountIban;
        this.movementDate = movementDate;
        this.amount = amount;
        this.concept = concept;
    }

    public BankMovement(Long id, MovementType movementType, PaymentMethod paymentMethod, String originAccountIban, String recipientAccountIban, Date movementDate, float amount, String concept) {
        this.id = id;
        this.movementType = movementType;
        this.paymentMethod = paymentMethod;
        this.originAccountIban = originAccountIban;
        this.recipientAccountIban = recipientAccountIban;
        this.movementDate = movementDate;
        this.amount = amount;
        this.concept = concept;
    }

    public Long getId() {
        return id;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOriginAccountIban() {
        return originAccountIban;
    }

    public void setOriginAccountIban(String originAccountIban) {
        this.originAccountIban = originAccountIban;
    }

    public Long getOriginCreditCardNumber() {
        return originCreditCardNumber;
    }

    public void setOriginCreditCardNumber(Long originCreditCardNumber) {
        this.originCreditCardNumber = originCreditCardNumber;
    }

    public String getRecipientAccountIban() {
        return recipientAccountIban;
    }

    public void setRecipientAccountIban(String recipientAccountIban) {
        this.recipientAccountIban = recipientAccountIban;
    }

    public Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(Date movementDate) {
        this.movementDate = movementDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
}
