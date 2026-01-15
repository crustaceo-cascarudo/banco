package com.fpmislata.banco.persistence.dao.impl.entity;

import com.fpmislata.banco.domain.enums.MovementType;
import com.fpmislata.banco.domain.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name="banking_movement")
public class BankMovementJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MovementType movementType;
    @NotNull
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @NotNull
    @Column (name = "origin_account")
    private String originAccountIban;
    @Column (name = "origin_card")
    private Long originCreditCardNumber;
    @NotNull
    @Column (name = "recipient_account")
    private String recipientAccountIban;
    @NotNull
    @Column(name = "movement_date")
    private Date movementDate;
    @NotNull
    private float amount;
    private String concept;

    public BankMovementJpaEntity() {
    }

    public BankMovementJpaEntity(Long id, MovementType movementType, PaymentMethod paymentMethod, String originAccountIban, Long originCreditCardNumber, String recipientAccountIban, Date movementDate, float amount, String concept) {
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

    public BankMovementJpaEntity(Long id, MovementType movementType, PaymentMethod paymentMethod, String originAccountIban, String recipientAccountIban, Date movementDate, float amount, String concept) {
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
